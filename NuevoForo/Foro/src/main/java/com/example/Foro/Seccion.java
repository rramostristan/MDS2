package com.example.Foro;

import org.orm.PersistentException;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import basededatos.BD_Principal;
import basededatos.SeccionDAO;

public class Seccion extends Seccion_ventana implements View {
	
	Button foro,ajustes,cerrarsesion,notificaciones,iniciarsesion,registrarse,creartema;
	HorizontalLayout parteregistrado,partenoregistrado;
	TextField tipotema,titulotema;
	VerticalLayout temas,creaciontema;
	Label tituloseccion;
	Navigator navegador;
	BD_Principal bd;
	MyUI m;
	int id,idu;
	
	public Seccion() {
		
	}
	
	public Seccion(MyUI myui) {
		// TODO Auto-generated constructor stub
		m=myui;
	}

	public void inicio(int idseccion, int idusuario) {
		bd = new BD_Principal();
		basededatos.Seccion seccion=null;
		id=idseccion;
		try {
			seccion = SeccionDAO.getSeccionByORMID(idseccion);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cerrarsesion=this.cerrarsesion_html;
		notificaciones=this.notificacion_html;
		ajustes=this.ajustes_html;
		foro=this.foro_html;
		iniciarsesion=this.iniciarsesion_html;
		registrarse=this.registrarse_html;
		
		tituloseccion=this.tituloseccion_html;
		creartema=this.creartema_html;
		temas=this.tema_html;
		tipotema=this.tipotema_html;
		titulotema=this.titulo_html;
		
		parteregistrado=this.parteregistrado_html;
		partenoregistrado=this.partenoregistrado_html;
		creaciontema=this.creaciontema_html;
		
		int i;
		tituloseccion.setValue(seccion.getTitulo_seccion());
		
		
		/*basededatos.Tema[] lista2 = bd.temasSeccion(idseccion);
		Vista_tema_heredar[] itemListaTemas = new Vista_tema_heredar[lista2.length];
		for(i=0;i<lista2.length;i++) {
			itemListaTemas[i] = new Vista_tema_heredar(m);
			itemListaTemas[i].setNavegador(navegador);
			itemListaTemas[i].inicio(lista2[i].getId_tema(), idusuario);
		}
		temas.addComponents(itemListaTemas);
		temas.setHeight(lista2.length*50 + "%");*/
		temasSeccion();
		
		if(idusuario==-1) {
			partenoregistrado.setVisible(true);
			parteregistrado.setVisible(false);
		}
		else {
			partenoregistrado.setVisible(false);
			parteregistrado.setVisible(true);
			creaciontema.setVisible(true);
		}
		
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
	
		iniciarsesion.addClickListener(e->{
			Iniciar_Sesion is =  new Iniciar_Sesion(m);
			is.inicio();
			m.setContent(is);
		});
	
		registrarse.addClickListener(e->{
			Registrarse reg =  new Registrarse(m);
			reg.inicio();
			m.setContent(reg);
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
		
		creartema.addClickListener(e->{
				crearTema();
		});
	}
	
	
	public void setNavegador(Navigator nav) {
		navegador=nav;
		
	}
	
	public void borrarSeccion() {
		new Vista_seccion_heredar().borrar_html.click();
	}
	
	public basededatos.Seccion conseguirSeccion() {
		try {
			return SeccionDAO.getSeccionByORMID(id);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void crearTema() {
		if(tipotema.getValue().equals("Privado")) {
			bd.crearTema(titulotema.getValue(), id,2);
		}
		else if(tipotema.getValue().equals("Oculto")) {
			bd.crearTema(titulotema.getValue(), id,1);
		}
		else {
			bd.crearTema(titulotema.getValue(), id,0);
		}
		Seccion seccion1 = new Seccion(m);
		seccion1.inicio(id, idu);
		m.setContent(seccion1);
	}

	public void temasSeccion() {
		basededatos.Tema[] lista2 = bd.temasSeccion(id);
		Vista_tema_heredar[] itemListaTemas = new Vista_tema_heredar[lista2.length];
		for(int i=0;i<lista2.length;i++) {
			itemListaTemas[i] = new Vista_tema_heredar(m);
			itemListaTemas[i].setNavegador(navegador);
			itemListaTemas[i].inicio(lista2[i].getId_tema(), idu);
		}
		temas.addComponents(itemListaTemas);
		temas.setHeight(lista2.length*50 + "%");
	}
}