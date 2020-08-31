package com.example.Foro;

import java.util.List;

import org.orm.PersistentException;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import basededatos.BD_Principal;
import basededatos.UsuarioDAO;
import basededatos.iUsuario_Registrado;

public class Usuario extends Usuario_ventana implements View {
	
	
	
	Button mensajes,amigos,perfil,foro,ajustes,notificaciones,cerrarsesion,iniciarsesion,registrarse;
	VerticalLayout vistaamigos,vistamensajes,vistaperfil,listaamigos,listamensajes;
	Button reportar,banear,agregar;
	HorizontalLayout partenoregistrado,parteregistrado;
	Label nombreusuario;
	Navigator navegador;
	TextField email;
	BD_Principal bd;
	MyUI m;
	int id1,id2;
	
	public Usuario() {
		
	}
	
	public Usuario(MyUI myui) {
		// TODO Auto-generated constructor stub
		m=myui;
	}

	public void inicio(int idusuariove, int idusuariovisto, int tipo) {
		id1=idusuariove;
		id2=idusuariovisto;
		foro=this.foro_html;
		ajustes=this.ajustes_html;
		notificaciones=this.notificacion_html;
		cerrarsesion=this.cerrarsesion_html;
		iniciarsesion=this.iniciarsesion_html;
		registrarse=this.registrarse_html;
		
		vistaamigos=this.vista_amigos_html;
		vistamensajes=this.vista_mensajes_html;
		vistaperfil=this.vista_perfil_html;
		listaamigos=this.listaamigos_html;
		listamensajes=this.listamensajes_html;
		partenoregistrado=this.partenoregistrado_html;
		parteregistrado=this.parteregistrado_html;
		
		nombreusuario=this.nombreusuario_html;
		amigos=this.amigos_html;
		perfil=this.perfil_html;
		mensajes=this.mensajes_html;
		email=this.nombreusuario_html1;
		reportar=this.reportar_html;
		banear=this.banear_html;
		agregar=this.agregar_html;
		
		bd = new BD_Principal();
		boolean registrado;
		basededatos.Usuario userve=null;
		basededatos.Usuario uservisto=null;
		
		if(idusuariove==-1)
			registrado=false;
		else
			registrado=true;
		
		try {
			if(registrado) 
				userve=UsuarioDAO.getUsuarioByORMID(idusuariove);
			uservisto=UsuarioDAO.getUsuarioByORMID(idusuariovisto);
		} catch (PersistentException e) {
			e.printStackTrace();
		}
		
		if(!registrado) {
			parteregistrado.setVisible(false);
			partenoregistrado.setVisible(true);
			reportar.setVisible(false);
			banear.setVisible(false);
			agregar.setVisible(false);
		}
		nombreusuario.setValue("Visualizando al usuario : " + uservisto.getNombre_usuario());
		
		
		//Vista perfil
		if(tipo==1) {
			vistaamigos.setVisible(false);
			vistamensajes.setVisible(false);
			vistaperfil.setVisible(true);
			email.setValue(uservisto.getEmail());
		}
		
		//Vista de lista de mensajes
		else if(tipo == 2) {
			vistaamigos.setVisible(false);
			vistamensajes.setVisible(true);
			vistaperfil.setVisible(false);
			
			int numeromaximo = new Ajustes_administrador().numeroMensajes;
			basededatos.Mensaje[] listaMensajes = bd.mensajesUsuario(idusuariovisto);
			System.out.println(listaMensajes.length);
			if(numeromaximo>listaMensajes.length) {
				numeromaximo=listaMensajes.length;
			}
			Vista_mensaje_heredar[] listaItems = new Vista_mensaje_heredar[numeromaximo];
			for(int i=0;i<numeromaximo;i++) {
				listaItems[i]=new Vista_mensaje_heredar(m);
				listaItems[i].setNavegador(navegador);
				listaItems[i].inicio(listaMensajes[i].getId_mensaje(),idusuariove);
			}
			listamensajes.addComponents(listaItems);
			listamensajes.setHeight(listaMensajes.length*60 + "%");
		}
		
		//Vista de lista de amigos
		else {
			vistaamigos.setVisible(true);
			vistamensajes.setVisible(false);
			vistaperfil.setVisible(false);
			
			basededatos.Usuario[] lista = bd.listaAmigos(idusuariovisto);
		    Vista_usuario_heredar[] listaItems = new Vista_usuario_heredar[lista.length];
		    for(int i=0;i<lista.length;i++) {
		    	listaItems[i]=new Vista_usuario_heredar(m);
		    	listaItems[i].setNavegador(navegador);
		    	listaItems[i].inicio(idusuariove,lista[i].getId_usuario(),0);
		    }
		    listaamigos.addComponents(listaItems);
		    listaamigos.setHeight(lista.length*20 + "%");
		}
		
		if(idusuariove==idusuariovisto) {
			vistaamigos.setVisible(false);
			vistamensajes.setVisible(false);
			vistaperfil.setVisible(false);
		}
		foro.addClickListener(e->{
				Visualizar v =  new Visualizar(m);
				v.inicio(idusuariove);
				m.setContent(v);			
		});
		
		notificaciones.addClickListener(e->{
				Notificaciones notificaciones =  new Notificaciones(m);
				notificaciones.inicio(idusuariove);
				m.setContent(notificaciones);
		});
		
		cerrarsesion.addClickListener(e->{
				Visualizar v =  new Visualizar(m);
				v.inicio(-1);
				m.setContent(v);
		});
		
		if(bd.listaAdministradores().contains(idusuariove)) {
			ajustes.addClickListener(e-> {
					Ajustes_administrador ajustes =  new Ajustes_administrador(m);
					ajustes.inicio(idusuariove,1);
					m.setContent(ajustes);
			});
		}
		else if(!bd.listaAdministradores().contains(idusuariove) && bd.listaModeradores().contains(idusuariove)) {
			banear.setVisible(false);
			ajustes.addClickListener(e-> {
					Ajustes_moderador ajustes =  new Ajustes_moderador(m);
					ajustes.inicio(idusuariove,1);
					m.setContent(ajustes);
			});
		}
		else {
			banear.setVisible(false);
			ajustes.addClickListener(e-> {
					Ajustes ajustes =  new Ajustes(m);
					ajustes.inicio(idusuariove,1);
					m.setContent(ajustes);		
			});
		}
		
		iniciarsesion.addClickListener(e-> {
				Iniciar_Sesion is =  new Iniciar_Sesion(m);
				is.inicio();
				m.setContent(is);
		});
		
		registrarse.addClickListener(e->{
				Registrarse reg =  new Registrarse(m);
				reg.inicio();
				m.setContent(reg);
		});
		
		Boolean baneado=false;
		basededatos.Usuario[] us = bd.usuariosBaneados();
		for(int i=0;i<us.length;i++) {
			if(us[i].equals(uservisto))
				baneado=true;
		}
		if(baneado) {
			banear.setCaption("Desbanear");
			banear.addClickListener(e-> {
					bd.desbanearUsuario(idusuariovisto);
					Usuario usuario = new Usuario(m);
					usuario.inicio(idusuariove,idusuariovisto,1);
					m.setContent(usuario);
			});
		}
		else {
			banear.addClickListener(e-> {
					bd.banearUsuario(idusuariovisto);
					Usuario usuario = new Usuario(m);
					usuario.inicio(idusuariove,idusuariovisto,1);
					m.setContent(usuario);
			});
		}
		if(registrado) {
			
		
		if(userve.reportados.contains(uservisto)) {
			reportar.setCaption("Reportado");
		}
		else {
			reportar.addClickListener(e-> {
					bd.Reportar(idusuariovisto, idusuariove);
					Usuario usuario = new Usuario(m);
					usuario.inicio(idusuariove,idusuariovisto,1);
					m.setContent(usuario);	
			});
		}
		
		
		
		
		if(userve.amigo.contains(uservisto)) {
			agregar.setCaption("Quitar de amigo");
			agregar.addClickListener(e-> {
					bd.eliminarAmigo(idusuariovisto, idusuariove);
					Usuario usuario = new Usuario(m);
					usuario.inicio(idusuariove,idusuariovisto,1);
					m.setContent(usuario);
			});
		}
		else {
			agregar.addClickListener(e-> {
					bd.agregarAmigo(idusuariove, idusuariovisto);
					Usuario usuario = new Usuario();
					usuario.inicio(idusuariove,idusuariovisto,1);
					m.setContent(usuario);
			});
		}
		}
		mensajes.addClickListener(e->{
				Usuario usuario = new Usuario(m);
				usuario.inicio(idusuariove,idusuariovisto,2);
				m.setContent(usuario);
		});
		amigos.addClickListener(e-> {
				Usuario usuario = new Usuario(m);
				usuario.inicio(idusuariove,idusuariovisto,3);
				m.setContent(usuario);
		});
		
		perfil.addClickListener(e-> {
				Usuario usuario = new Usuario(m);
				usuario.inicio(idusuariove,idusuariovisto,1);
				m.setContent(usuario);
		});
		
	}
	
	public void agregarAmigo() {
		bd.agregarAmigo(id2, id1);
		Usuario usuario = new Usuario(m);
		usuario.inicio(id1,id2,1);
		m.setContent(usuario);
	}
	
	public basededatos.Usuario conseguirUsuario() {
		try {
			return UsuarioDAO.getUsuarioByORMID(id1);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void eliminarAmigo() {
		bd.eliminarAmigo(id2, id1);
		Usuario usuario = new Usuario(m);
		usuario.inicio(id1,id2,1);
		m.setContent(usuario);
	}

	

	public List<Integer> listaAdministradores() {
		return new BD_Principal().listaAdministradores();
	}
	
	public List<Integer> listaModeradores() {
		return new BD_Principal().listaModeradores();
	}

	public void Reportar() {
		bd.Reportar(id1, id2);
		Usuario usuario = new Usuario(m);
		usuario.inicio(id1,id2,1);
		m.setContent(usuario);
	}
	
	public Mensaje[] mensajesUsuario() {
		return null;
	}
	public void setNavegador(Navigator nav) {
		navegador=nav;
	}
}