package com.example.Foro;

import org.orm.PersistentException;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import basededatos.BD_Principal;
import basededatos.MensajeDAO;

public class Responder extends Responder_ventana implements View{
	public Mensaje _unnamed_Mensaje_;
	public Responder _unnamed_Responder_;

	Button ajustes,notificaciones,cerrarsesion,foro,publicar,titulotema;
	VerticalLayout mensajes;
	Navigator navegador;
	TextField comentario;
	BD_Principal bd;
	basededatos.Mensaje mensaje;
	MyUI m;
	
	public Responder(MyUI myui) {
		m=myui;
	}
	
	public void inicio(int idusuario,int idmensaje) {
		foro=this.foro_html;
		ajustes=this.ajustes_html;
		notificaciones=this.notificaciones_html;
		cerrarsesion=this.cerrarsesion_html;
		publicar=this.publicar_html;
		comentario=this.contenidocomentario_html;
		titulotema=this.titulotema_html;
		mensajes=this.mensajes_html;
		bd = new BD_Principal();
		mensaje=null;
		try {
			mensaje = MensajeDAO.getMensajeByORMID(idmensaje);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		titulotema.setCaption(mensaje.getEs_de().getTitulo_tema());
		
		basededatos.Mensaje[] listamensajes = new basededatos.Mensaje[bd.respuestas(mensaje.getId_mensaje()).size()];
		for(int i=0;i<listamensajes.length;i++) {
			listamensajes[i]=bd.respuestas(mensaje.getId_mensaje()).get(i);
		}
		
		Vista_mensaje_heredar[] itemLista = new Vista_mensaje_heredar[listamensajes.length];
		for(int i=0;i<listamensajes.length;i++) {
			itemLista[i] = new Vista_mensaje_heredar(m);
			itemLista[i].setNavegador(navegador);
			itemLista[i].inicio(listamensajes[i].getId_mensaje(),idusuario);
		}
		mensajes.addComponents(itemLista);
		mensajes.setHeight(listamensajes.length*70 + "%");
		
		foro.addClickListener(e->{
				Visualizar v =  new Visualizar(m);
				v.inicio(idusuario);
				m.setContent(v);
		});
	
	
		notificaciones.addClickListener(e->{
			Notificaciones notificaciones =  new Notificaciones(m);
			notificaciones.inicio(idusuario);
			m.setContent(notificaciones);
		});
	
	
		cerrarsesion.addClickListener(e->{
			Visualizar v =  new Visualizar(m);
			v.inicio(-1);
			m.setContent(v);
		});
		
		if(bd.listaAdministradores().contains(idusuario)) {
			ajustes.addClickListener(e-> {
					Ajustes_administrador ajustes =  new Ajustes_administrador(m);
					ajustes.inicio(idusuario,1);
					m.setContent(ajustes);
			});
		}
		else if(!bd.listaAdministradores().contains(idusuario) && bd.listaModeradores().contains(idusuario)) {
			ajustes.addClickListener(e-> {
					Ajustes_moderador ajustes =  new Ajustes_moderador(m);
					ajustes.inicio(idusuario,1);
					m.setContent(ajustes);
			});
			
		}
		else {
			ajustes.addClickListener(e-> {
				Ajustes ajustes =  new Ajustes(m);
				ajustes.inicio(idusuario,1);
				m.setContent(ajustes);
			
		});
		}
		
		titulotema.addClickListener(e-> {
				Tema tema = new Tema(m);
				tema.inicio(mensaje.getEs_de().getId_tema(), idusuario);
				m.setContent(tema);
				
		});
		
		publicar.addClickListener(e->{
				bd.crearMensaje(comentario.getValue(), idusuario, mensaje.getEs_de().getId_tema(), mensaje.getId_mensaje());
				Responder responder = new Responder(m);
				responder.inicio(idusuario, idmensaje);
				m.setContent(responder);
		});
	}
	
	public void setNavegador(Navigator nav) {
		navegador=nav;
	}
	
	/*public void crearMensaje(Crear_mensaje aCreacion) {
		throw new UnsupportedOperationException();
	}*/
}