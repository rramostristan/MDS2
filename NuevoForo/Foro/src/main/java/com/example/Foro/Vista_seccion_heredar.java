package com.example.Foro;

import org.orm.PersistentException;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import basededatos.BD_Principal;
import basededatos.SeccionDAO;

public class Vista_seccion_heredar extends Vista_seccion_ventana implements View {
	Label nombre,numeromensajes,numerotemas;
	Button borrar,nombreseccion;
	Navigator navegador;
	BD_Principal bd;
	MyUI m;
	
	public Vista_seccion_heredar() {
		
	}
	
	public Vista_seccion_heredar(MyUI myui) {
		// TODO Auto-generated constructor stub
		m=myui;
	}

	public void inicio(int idseccion, int idusuario) {

		basededatos.Seccion seccion=null;
		try {
			seccion = SeccionDAO.getSeccionByORMID(idseccion);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nombre = this.nombre_html;
		nombre.setValue(seccion.getTitulo_seccion());
		borrar=this.borrar_html;
		nombreseccion=this.botonnombreseccion_html;
		nombreseccion.setCaption(seccion.getTitulo_seccion());
		nombre.setVisible(false);
		numerotemas=this.numerotemas_html;
		numerotemas.setValue("Numero temas: "+seccion.tiene.toArray().length);
		bd= new BD_Principal();
		nombreseccion.addClickListener(e-> {
				Seccion nseccion =  new Seccion(m);
				nseccion.inicio(idseccion, idusuario);
				m.setContent(nseccion);
		});
		borrar.addClickListener(e-> {
				bd.borrarSeccion(idseccion);
				Secciones secciones =  new Secciones(m);
				secciones.inicio(idusuario);
				m.setContent(secciones);
		});
		if(!bd.listaAdministradores().contains(idusuario)) {
			borrar.setVisible(false);
		}
	}
	
	public void setNavegador(Navigator nav) {
		navegador=nav;
		
	}

}
