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
import basededatos.UsuarioDAO;

public class Ajustes_moderador extends Ajustes_moderador_ventana implements View {
	
	Button ajustes,notificacion,cerrarsesion,foro;
	Button usuariosreportados,amigos,listausuarios,perfil;
	Button modificarcontrasena,modificarperfil;
	VerticalLayout listadeusuarios,listaamigos,listareportados;
	VerticalLayout vistaperfil,vistalista,vistaamigos,vistareportados;
	Label nombreusuario,email,contrasena;
	Navigator navegador;
	BD_Principal bd;
	MyUI m;
	
	
	public Ajustes_moderador(){
		
	}
	
	public Ajustes_moderador(MyUI myui) {
		// TODO Auto-generated constructor stub
		m=myui;
	}

	public void inicio(int idusuario,int tipo) {
		
		bd = new BD_Principal();
		foro=this.foro_html;
		ajustes=this.ajustes_html;
		notificacion=this.notificaciones_html;
		cerrarsesion=this.cerrarsesion_html;
		
		vistaperfil=this.vista_perfil_html;
		vistalista=this.vista_lista_usuarios_html;
		vistaamigos=this.vista_lista_amigos_html;
		vistareportados=this.vista_usuarios_reportados_html;
		
		listadeusuarios=this.listausuarios_html;
		listaamigos=this.listaamigos_html;
		listareportados=this.listausuariosreportados_html;
		
		nombreusuario=this.nombreusuario_html;
		email=this.emailusuario_htmlm;
		contrasena=this.contrasena_html;
		modificarperfil=this.modificarperfil_html;
		modificarcontrasena=this.modificarcontrasena_html;
	
		listausuarios=this.botonlistausuarios_html;
		usuariosreportados=this.usuariosreportados_html;
		amigos=this.amigos_html;
		perfil=this.perfil_html;
		
		basededatos.Usuario user=null;
		try {
			user = UsuarioDAO.getUsuarioByORMID(idusuario);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Vista perfil
		if(tipo==1) {
			vistaperfil.setVisible(true);
			vistalista.setVisible(false);
			vistaamigos.setVisible(false);
			vistareportados.setVisible(false);
			nombreusuario.setValue(user.getNombre_usuario());
			email.setValue(user.getEmail());
			contrasena.setValue(user.getContrasena());
		}
		
		//Vista de lista de usuarios
		else if(tipo==2) {
			vistaperfil.setVisible(false);
			vistalista.setVisible(true);
			vistaamigos.setVisible(false);
			vistareportados.setVisible(false);
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
			vistaperfil.setVisible(false);
			vistalista.setVisible(false);
			vistaamigos.setVisible(true);
			vistareportados.setVisible(false);
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
		
		//Vista de lista de usuarios reportados
		else {
			vistaperfil.setVisible(false);
			vistalista.setVisible(false);
			vistaamigos.setVisible(false);
			vistareportados.setVisible(true);
			basededatos.Usuario[] usuarios = bd.usuariosReportados();
			Vista_usuario_heredar[] listaItems = new Vista_usuario_heredar[usuarios.length];
			for(int i=0;i<usuarios.length;i++) {
				listaItems[i]=new Vista_usuario_heredar(m);
				listaItems[i].setNavegador(navegador);
				listaItems[i].inicio(idusuario,	usuarios[i].getId_usuario(),1);
			}
					
			listareportados.addComponents(listaItems);
			listareportados.setHeight(usuarios.length*40 + "%");
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
					Ajustes_moderador ajustes =  new Ajustes_moderador(m);
					ajustes.inicio(idusuario,1);
					m.setContent(ajustes);
				});
		
		
		perfil.addClickListener(e->{
				Ajustes_moderador ajustesperfil =  new Ajustes_moderador(m);
				ajustesperfil.inicio(idusuario,1);
				m.setContent(ajustesperfil);
		});
		
		listausuarios.addClickListener(e-> {
				Ajustes_moderador ajustesusuarios =  new Ajustes_moderador(m);
				ajustesusuarios.inicio(idusuario,2);
				m.setContent(ajustesusuarios);
		});
		
		amigos.addClickListener(e-> {
				Ajustes_moderador ajustesamigos =  new Ajustes_moderador(m);
				ajustesamigos.inicio(idusuario,3);
				m.setContent(ajustesamigos);
		});
		
		usuariosreportados.addClickListener(e-> {
				Ajustes_moderador ajustesreportados =  new Ajustes_moderador(m);
				ajustesreportados.inicio(idusuario,4);
				m.setContent(ajustesreportados);
				
		});
		
		modificarperfil.addClickListener(e-> {
				Modificar_perfil mperfil = new Modificar_perfil(m);
				mperfil.inicio(idusuario);
				m.setContent(mperfil);
				
		});
		
		modificarcontrasena.addClickListener(e-> {
				Modificar_contrasena mcontrasena = new Modificar_contrasena(m);
				mcontrasena.inicio(idusuario);
				m.setContent(mcontrasena);
		});
	}
	
	public void setNavegador(Navigator nav) {
		navegador=nav;
	}
}