package com.example.Foro;

import org.orm.PersistentException;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;

import basededatos.BD_Principal;
import basededatos.TemaDAO;

public class Vista_tema_heredar extends Vista_tema_ventana implements View {

	Label nombre,mensajes,likes;
	Button borrar,like,nombreboton;
	Navigator navegador;
	BD_Principal bd;
	basededatos.Tema tema;
	MyUI m;
	
	public Vista_tema_heredar() {
		
	}

	public Vista_tema_heredar(MyUI myui) {
		// TODO Auto-generated constructor stub
		m=myui;
	}

	public void inicio(int idtema, int idusuario) {
		tema=null;
		try {
			tema=TemaDAO.getTemaByORMID(idtema);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mensajes=this.numeromensajes_html;
		likes=this.numerolikes_html;
		borrar=this.borrar_html;
		like=this.like_html;
		nombreboton=this.nombretemaboton_html;
		bd = new BD_Principal();
		
		nombreboton.setCaption(tema.getTitulo_tema());
		likes.setValue("Numlikes:"+tema.getNum_likes_tema());
		mensajes.setValue("Numero mensajes:"+tema.tiene.toArray().length);
	
		nombreboton.addClickListener(e-> {
				Tema tema =  new Tema(m);
				tema.inicio(idtema, idusuario);
				m.setContent(tema);
		});
		
		like.addClickListener(e-> {
				bd.gustarTema(idtema, idusuario);
				likes.setValue("Numlikes:"+tema.getNum_likes_tema());
		});
		
		borrar.addClickListener(e-> {
				bd.borrarTema(idtema);
				borrar.setCaption("Borrado");
		});
		
		if(idusuario==-1) {
			borrar.setVisible(false);
			like.setVisible(false);
		}
		
		else if(bd.listaAdministradores().contains(idusuario)) {
			
		}
		else{
			borrar.setVisible(false);
		}
	}
	
	
	public void setNavegador(Navigator nav) {
		navegador=nav;
	}
}
