package basededatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.orm.PersistentException;


public class BD_Usuarios {
	public BD_Principal _bD_Principal;
	public Vector<Usuario> _usuarios = new Vector<Usuario>();

	public void agregarAmigo(int aId_usuario1, int aId_usuario2) {
		try {
			Usuario user1 = UsuarioDAO.getUsuarioByORMID(aId_usuario1);
			Usuario user2 = UsuarioDAO.getUsuarioByORMID(aId_usuario2);
			user1.es_amigo_de.add(user2);
			user2.es_amigo_de.add(user1);
			UsuarioDAO.save(user1);
			BD_Notificaciones bdnotificaciones = new BD_Notificaciones();
			bdnotificaciones.crearNotificacion(aId_usuario1, "Eres amigo del usuario " + user2.getNombre_usuario());
			
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void banearUsuario(int aId_usuario) {
		try {
			Usuario user = UsuarioDAO.getUsuarioByORMID(1);
			user.baneados.add(UsuarioDAO.getUsuarioByORMID(aId_usuario));
			UsuarioDAO.save(user);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void eliminarUsuario(int idusuario) {
		try {
			Usuario user = UsuarioDAO.getUsuarioByORMID(idusuario);
			UsuarioDAO.deleteAndDissociate(user);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void desbanearUsuario(int aId_usuario) {
		try {
			Usuario user = UsuarioDAO.getUsuarioByORMID(1);
			user.baneados.remove(UsuarioDAO.getUsuarioByORMID(aId_usuario));
			UsuarioDAO.save(user);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminarAmigo(int aId_usuario1, int aId_usuario2) {
		try {
			Usuario user1 = UsuarioDAO.getUsuarioByORMID(aId_usuario1);
			Usuario user2 = UsuarioDAO.getUsuarioByORMID(aId_usuario2);
			user1.es_amigo_de.remove(user2);
			user1.amigo.remove(user2);
			UsuarioDAO.save(user1);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarSolicitud(int aId_usuario1, int aId_usuario2) {
		throw new UnsupportedOperationException();
	}

	public int iniciarSesion(String aUsuario, String aContrasena) {
		try {
			Usuario[] users = UsuarioDAO.listUsuarioByCriteria(new UsuarioCriteria());
			for(Usuario user : users) {
				if(user.getNombre_usuario().equals(aUsuario)) {
					if(user.getContrasena().equals(aContrasena))
						return user.getId_usuario();
				}
			}
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	public boolean modificarContrasena(String aAntiguaC, String aNuevaC, int aId_usuario) {
		Usuario user;
		try {
			user = UsuarioDAO.getUsuarioByORMID(aId_usuario);
			if(aAntiguaC.equals(user.getContrasena())) {
				user.setContrasena(aNuevaC);
				UsuarioDAO.save(user);
				return true;
			}
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return false;
	}

	public boolean Registrarse(String aUsuario, String aContrasena, String aEmail) {
		Usuario user = new Usuario();
		user.setContrasena(aContrasena);
		user.setNombre_usuario(aUsuario);
		try {
			UsuarioDAO.save(user);
			return true;
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Usuario[] listaAmigos(int aId_usuario) {
		Usuario[] listaUsuarios;
		Usuario user;
		try {
			user = UsuarioDAO.getUsuarioByORMID(aId_usuario);
			listaUsuarios=user.es_amigo_de.toArray();
			return listaUsuarios;
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Usuario[] listaUsuarios(int id) {
		try {
			
			Usuario[] users = UsuarioDAO.listUsuarioByCriteria(new UsuarioCriteria());
			Usuario[] u = new Usuario[users.length-1];
			if(id==-1) {
				return users;
			}
			int j=0;
			for(int i=0;i<users.length;i++) {
				if(users[i].equals(UsuarioDAO.getUsuarioByORMID(id))==false) {
					u[j]=users[i];
					j++;
				}
			}
			return u;
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Usuario[] usuariosBaneados() {
		Usuario[] usuariosbaneados = null;
		try {
			usuariosbaneados=UsuarioDAO.getUsuarioByORMID(1).baneados.toArray();
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuariosbaneados;
	}

	public Usuario[] usuariosReportados() {
		Usuario[] usuariosbaneados = null;
		List<Integer> lista = new ArrayList<Integer>();
		try {
			Usuario[] users = UsuarioDAO.listUsuarioByCriteria(new UsuarioCriteria());
			for(int i=0;i<users.length;i++) {
				Usuario[] aux = users[i].reportados.toArray();
				for(int j=0;j<aux.length;j++) {
					if(lista.contains(aux[j].getId_usuario())==false) {
						lista.add(aux[j].getId_usuario());
					}
				}
			}
		} catch (PersistentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		usuariosbaneados= new Usuario[lista.size()];
		for(int i=0;i<usuariosbaneados.length;i++) {
			try {
				usuariosbaneados[i]=UsuarioDAO.getUsuarioByORMID(lista.get(i));
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		System.out.println(usuariosbaneados.toString());
		return usuariosbaneados;
	}

	public void Reportar(int aId_reportado, int aId_reportador) {
		try {
			Usuario user = UsuarioDAO.getUsuarioByORMID(aId_reportador);
			user.reportados.add(UsuarioDAO.getUsuarioByORMID(aId_reportado));
			UsuarioDAO.save(user);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Usuario conseguirUsuario(int aId_usuario) {
		try {
			return UsuarioDAO.getUsuarioByORMID(aId_usuario);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void quitarReportado(int idusuario) {
			Usuario[] usuariosbaneados = null;
			List<Integer> lista = new ArrayList<Integer>();
			basededatos.Usuario user = null;
			try {
				user = UsuarioDAO.getUsuarioByORMID(idusuario);
				Usuario[] users = UsuarioDAO.listUsuarioByCriteria(new UsuarioCriteria());
				for(int i=0;i<users.length;i++) {
					users[i].reportados.remove(user);
					UsuarioDAO.save(users[i]);
				}
			} catch (PersistentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}