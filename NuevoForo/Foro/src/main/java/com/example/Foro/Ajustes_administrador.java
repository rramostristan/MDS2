package com.example.Foro;

import org.orm.PersistentException;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import basededatos.BD_Principal;
import basededatos.MediaDAO;
import basededatos.UsuarioDAO;

public class Ajustes_administrador extends Ajustes_administrador_ventana implements View {
	
	Button ajustes,notificacion,cerrarsesion,foro;
	Button usuariosreportados,amigos,usuariosbaneados,modificarmensajes,listausuarios,perfil;
	Button modificarcontrasena,modificarperfil,cambiar;
	VerticalLayout listadeusuarios,listaamigos,listareportados,listabaneados,listamensaje;
	VerticalLayout vistaperfil,vistalista,vistaamigos,vistareportados,vistabaneados,vistamensajes;
	Label nombreusuario,email,contrasena;
	TextField numeromensajes;
	Navigator navegador;
	BD_Principal bd;
	int numeroMensajes;
	int idusuario;
	MyUI m;
	
	public Ajustes_administrador() {
		try {
			numeroMensajes=Integer.parseInt(MediaDAO.getMediaByORMID(1).getEnlace());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}
	
	public Ajustes_administrador(MyUI myui) {
		// TODO Auto-generated constructor stub
		m=myui;
		try {
			numeroMensajes=Integer.parseInt(MediaDAO.getMediaByORMID(1).getEnlace());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

	public void inicio(int idusuario, int tipo) {
		bd = new BD_Principal();
		foro=this.foro_html;
		ajustes=this.ajustes_html;
		notificacion=this.notificaciones_html;
		cerrarsesion=this.cerrarsesion_html;
		
		vistaperfil=this.vista_perfil_html;
		vistalista=this.vista_lista_usuarios_html;
		vistaamigos=this.vista_amigos_html;
		vistareportados=this.vista_usuarios_revision_html;
		vistabaneados=this.vista_usuarios_baneados_html;
		vistamensajes=this.vista_mensajes_html;
		
		listadeusuarios=this.listausuarios_html;
		listaamigos=this.listaamigos_html;
		listareportados=this.listausuariosrevision_html;
		listabaneados=this.listaususariosbaneados_html;
		nombreusuario=this.nombreusuario_html;
		email=this.emailusuario_htmlm;
		contrasena=this.contrasena_html;
		numeromensajes=this.numeromensajes_html;
		cambiar=this.cambiar_html;
		modificarcontrasena=this.mcontrasena_html;
		modificarperfil=this.mperfil_html;

	
		listausuarios=this.usuarios_html;
		modificarmensajes=this.mensajes_html;
		usuariosbaneados=this.baneados_html;
		usuariosreportados=this.reportados_html;
		amigos=this.amigos_html;
		perfil=this.perfil_html;
		
		basededatos.Usuario user=null;
		try {
			user = UsuarioDAO.getUsuarioByORMID(idusuario);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Vista del perfil
		if(tipo==1) {
			vistareportados.setVisible(false);
			vistabaneados.setVisible(false);
			vistamensajes.setVisible(false);
			vistalista.setVisible(false);
			vistaamigos.setVisible(false);
			vistaperfil.setVisible(true);
			nombreusuario.setValue(user.getNombre_usuario());
			email.setValue(user.getEmail());
			contrasena.setValue(user.getContrasena());
		}
			
		//Vista de lista de usuarios
		else if(tipo==2) {
			vistareportados.setVisible(false);
			vistabaneados.setVisible(false);
			vistamensajes.setVisible(false);
			vistalista.setVisible(true);
			vistaamigos.setVisible(false);
			vistaperfil.setVisible(false);
			basededatos.Usuario[] usuarios=bd.listaUsuarios(idusuario);
			Vista_usuario_heredar[] listaItems = new Vista_usuario_heredar[usuarios.length];
			for(int i=0;i<usuarios.length;i++) {
				listaItems[i]=new Vista_usuario_heredar(m);
				listaItems[i].setNavegador(navegador);
				listaItems[i].inicio(idusuario,usuarios[i].getId_usuario(),0);
				}
			listadeusuarios.addComponents(listaItems);
			listadeusuarios.setHeight(usuarios.length*20 + "%");
		}
		
		//Vista de lista de amigos
		else if(tipo==3) {
			vistareportados.setVisible(false);
			vistabaneados.setVisible(false);
			vistamensajes.setVisible(false);
			vistalista.setVisible(false);
			vistaamigos.setVisible(true);
			vistaperfil.setVisible(false);
			basededatos.Usuario[] usuarios=bd.listaAmigos(idusuario);
			System.out.println(usuarios.length);
			Vista_usuario_heredar[] listaItems = new Vista_usuario_heredar[usuarios.length];
			for(int i=0;i<usuarios.length;i++) {
				listaItems[i]=new Vista_usuario_heredar(m);
				listaItems[i].setNavegador(navegador);
				listaItems[i].inicio(idusuario,usuarios[i].getId_usuario(),0);
			}
				
			listaamigos.addComponents(listaItems);
			listaamigos.setHeight(usuarios.length*20 + "%");
		}
		
		//Vista de lista de reportados
		else if(tipo==4) {
			vistareportados.setVisible(true);
			vistabaneados.setVisible(false);
			vistamensajes.setVisible(false);
			vistalista.setVisible(false);
			vistaamigos.setVisible(false);
			vistaperfil.setVisible(false);
			basededatos.Usuario[] usuarios = bd.usuariosReportados();
			Vista_usuario_heredar[] listaItems = new Vista_usuario_heredar[usuarios.length];
			for(int i=0;i<usuarios.length;i++) {
				listaItems[i]=new Vista_usuario_heredar(m);
				listaItems[i].setNavegador(navegador);
				listaItems[i].inicio(idusuario,usuarios[i].getId_usuario(),1);
			}
					
			listareportados.addComponents(listaItems);
			listareportados.setHeight(usuarios.length*40 + "%");
		}
		
		//Vista de lista de baneados
		else if(tipo==5) {
			vistareportados.setVisible(false);
			vistabaneados.setVisible(true);
			vistamensajes.setVisible(false);
			vistalista.setVisible(false);
			vistaamigos.setVisible(false);
			vistaperfil.setVisible(false);
			basededatos.Usuario[] usuarios = bd.usuariosBaneados();
			Vista_usuario_heredar[] listaItems = new Vista_usuario_heredar[usuarios.length];
			for(int i=0;i<usuarios.length;i++) {
				listaItems[i]=new Vista_usuario_heredar(m);
				listaItems[i].setNavegador(navegador);
				listaItems[i].inicio(idusuario,usuarios[i].getId_usuario(),2);
			}
					
			listabaneados.addComponents(listaItems);
			listabaneados.setHeight(usuarios.length*40 + "%");
		}
		
		//Vista numero mensajes
		else {
			vistareportados.setVisible(false);
			vistabaneados.setVisible(false);
			vistamensajes.setVisible(true);
			vistalista.setVisible(false);
			vistaamigos.setVisible(false);
			vistaperfil.setVisible(false);
			try {
				numeromensajes.setValue(numeroMensajes+"");
			}catch(NullPointerException e) {
				numeromensajes.setValue("No definido");
			}
		}
		foro.addClickListener(e->{
			Visualizar v =  new Visualizar(m);
			v.inicio(idusuario);
			m.setContent(v);
		});
	
		notificacion.addClickListener(e->{
			Notificaciones notificaciones =  new Notificaciones(m);
			notificaciones.inicio(idusuario);
			m.setContent(notificaciones);
		});


		cerrarsesion.addClickListener(e->{
			Visualizar v =  new Visualizar(m);
			v.inicio(-1);
			m.setContent(v);
		});
		
		ajustes.addClickListener(e-> {
					Ajustes_administrador ajustes =  new Ajustes_administrador(m);
					ajustes.inicio(idusuario,1);
					m.setContent(ajustes);
				});
		
		
		perfil.addClickListener(e->{
				Ajustes_administrador ajustesperfil =  new Ajustes_administrador(m);
				ajustesperfil.inicio(idusuario,1);
				m.setContent(ajustesperfil);
		});
		
		listausuarios.addClickListener(e-> {
				Ajustes_administrador ajustesusuarios =  new Ajustes_administrador(m);
				ajustesusuarios.inicio(idusuario,2);
				m.setContent(ajustesusuarios);
		});
		
		amigos.addClickListener(e-> {
				Ajustes_administrador ajustesamigos =  new Ajustes_administrador(m);
				ajustesamigos.inicio(idusuario,3);
				m.setContent(ajustesamigos);
		});
		
		usuariosreportados.addClickListener(e-> {
				/*Ajustes_administrador ajustesreportados =  new Ajustes_administrador(m);
				ajustesreportados.inicio(idusuario,4);
				m.setContent(ajustesreportados);*/
				usuariosReportados();
				
		});
		
		usuariosbaneados.addClickListener(e-> {
				/*Ajustes_administrador ajustesbaneados =  new Ajustes_administrador(m);
				ajustesbaneados.inicio(idusuario,5);
				m.setContent(ajustesbaneados);*/
				usuariosBaneados();
		});
		
		modificarmensajes.addClickListener(e-> {
				Ajustes_administrador ajustesmensajes =  new Ajustes_administrador(m);
				ajustesmensajes.inicio(idusuario,6);
				m.setContent(ajustesmensajes);
		});
		
		cambiar.addClickListener(e->{
				try {
					numeroMensajes=Integer.parseInt(numeromensajes.getValue());
				}catch(Exception i) {
					Ajustes_administrador ajustesmensajes =  new Ajustes_administrador(m);
					ajustesmensajes.inicio(idusuario,6);
					m.setContent(ajustesmensajes);
				}
				try {
					basededatos.Media m =MediaDAO.getMediaByORMID(1);
					m.setEnlace(numeroMensajes+"");
					MediaDAO.save(m);
				} catch (PersistentException e1) {
					e1.printStackTrace();
				}
				numeromensajes.setValue(numeroMensajes+"");
		});
		
		modificarperfil.addClickListener(e-> {
				Modificar_perfil mperfil = new Modificar_perfil(m);
				mperfil.inicio(idusuario);
				m.setContent(mperfil);	
		});
		
		modificarcontrasena.addClickListener(e->{
				Modificar_contrasena mcontrasena = new Modificar_contrasena(m);
				mcontrasena.inicio(idusuario);
				m.setContent(mcontrasena);
		});
	}
	
	public void banearUsuario() {
		new Usuario(m).banear_html.click();
	}
	
	public void desbanearUsuario() {
		new Usuario(m).banear_html.click();
	}

	public Usuario[] usuariosBaneados() {
		Ajustes_administrador ajustesbaneados =  new Ajustes_administrador(m);
		ajustesbaneados.inicio(idusuario,5);
		m.setContent(ajustesbaneados);
		return null;
	}

	public Usuario[] usuariosReportados() {
		Ajustes_administrador ajustesreportados =  new Ajustes_administrador(m);
		ajustesreportados.inicio(idusuario,4);
		m.setContent(ajustesreportados);
		return null;
	}
	
	public void setNavegador(Navigator nav) {
		navegador=nav;
	}
	
	public int numero() {
		return numeroMensajes;
	}
	
}