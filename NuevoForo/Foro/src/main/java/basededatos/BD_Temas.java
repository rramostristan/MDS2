package basededatos;

import java.util.Vector;

import org.orm.PersistentException;


public class BD_Temas {
	public BD_Principal _bD_Principal;
	public Vector<Tema> _temas = new Vector<Tema>();

	public void gustarTema(int aId_tema, int aId_usuario) {
		try {
			Usuario user = UsuarioDAO.getUsuarioByORMID(aId_usuario);
			Tema tema = TemaDAO.getTemaByORMID(aId_tema);
			if(tema.es_gustado.contains(user)==false) {
				tema.setNum_likes_tema(tema.getNum_likes_tema()+1);
				tema.es_gustado.add(user);
				user.gusta_a.add(tema);
				TemaDAO.save(tema);
				UsuarioDAO.save(user);
			}
			
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Tema[] listaTemas(int aNivel) {
		Tema[] temasocultos = null;
		Tema[] temaspublicos = null;
		Tema[] temasprivados = null;
		try {
			Tema tema = TemaDAO.getTemaByORMID(1);
			try {
			temasocultos = tema.temas_ocultos.toArray();
			temaspublicos = tema.temas_publicos.toArray();
			temasprivados = tema.temas_privados.toArray();
			}catch(NullPointerException e) {
				
			}
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(aNivel == 0) {
			return temaspublicos;
		}
		if(aNivel == 1) {
			
			return temasocultos;
		}
		if(aNivel == 2) {
			return temasprivados;
		}
		else {
			try {
				return TemaDAO.listTemaByCriteria(new TemaCriteria());
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	public void borrar_Tema(int aId_tema) {
		try {
			Tema tema = TemaDAO.getTemaByORMID(aId_tema);
			Mensaje[] m = tema.tiene.toArray();
			Tema t = TemaDAO.getTemaByORMID(1);
			if(t.temas_ocultos.contains(tema)) {
				t.temas_ocultos.remove(tema);
				TemaDAO.save(t);
			}
			else if(t.temas_privados.contains(tema)) {
				t.temas_privados.remove(tema);
				TemaDAO.save(t);
			}
			else if(t.temas_publicos.contains(tema)) {
				t.temas_publicos.remove(tema);
				TemaDAO.save(t);
			}
			if(m!=null) {
				for(int i=0;i<m.length;i++) {
					try {
					new BD_Mensajes().BorrarMensaje(m[i].getId_mensaje());
					}catch(Exception e) {
						continue;
					}
				}
			}
			
			TemaDAO.deleteAndDissociate(tema);
			
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Tema conseguirTema(int aId_tema) {
		Tema tema = null;
		try {
			tema = TemaDAO.getTemaByORMID(aId_tema);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tema;
	}

	public Tema[] temasSeccion(int aId_seccion) {
		BD_Secciones bd = new BD_Secciones();
		Seccion seccion = bd.conseguirSeccion(aId_seccion);
		return seccion.tiene.toArray();
	}

	public void crearTema(String aTitulo, int aId_seccion,int nivel) {
		try {
			Tema tema = new Tema();
			Tema uno = TemaDAO.getTemaByORMID(1);
			tema.setEs_de(SeccionDAO.getSeccionByORMID(aId_seccion));
			tema.setNum_likes_tema(0);
			tema.setTitulo_tema(aTitulo);
			
			if(nivel==0) {
				uno.temas_publicos.add(tema);
			}
			else if(nivel==1) {
				uno.temas_ocultos.add(tema);
			}
			else if(nivel==2) {
				uno.temas_privados.add(tema);
			}
			else {
				uno.temas_publicos.add(tema);
			}
			TemaDAO.save(tema);
			TemaDAO.save(uno);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}