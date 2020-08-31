package com.example.Foro;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

import basededatos.BD_Principal;

public class Iniciar_Sesion extends Iniciar_Sesion_ventana implements View {
	

	TextField nombre;
	PasswordField contrasena;
	Button entrar,registrarse;
	Navigator navegador;
	BD_Principal bd;
	MyUI m;
	boolean i;
	
	public Iniciar_Sesion(MyUI myui) {
		// TODO Auto-generated constructor stub
		m=myui;
	}

	public void Iniciar_Sesion() {
		
	}
	
	public void inicio() {
		nombre=this.nombre_html;
		contrasena=this.contrasena_html;
		entrar=this.entrar_html;
		registrarse=this.registrarse_html;
		bd = new BD_Principal();
		basededatos.Usuario[] usuarios = bd.usuariosBaneados();
		entrar.addClickListener(e-> {
				boolean reportado=false;
				int pasa =bd.iniciarSesion(nombre.getValue(), contrasena.getValue());
				System.out.println(bd.listaAdministradores().toString());
				for(int i=0;i<usuarios.length;i++) {
					if(usuarios[i].getId_usuario()==pasa) {
						reportado=true;
					}
				}
			if(pasa!=-1) {
				if(reportado) {
					nombre.setCaption("Lo sentimos, no puedes acceder");
				}
				else {
					Visualizar visualizar = new Visualizar(m);
					visualizar.inicio(pasa);
					m.setContent(visualizar);
					i=true;
				}
			}
			else {
				 nombre.setCaption("No pasastes");
			 }
		});
		
		
		registrarse.addClickListener(e-> {
			Registrarse r = new Registrarse(m);
			r.inicio();
			m.setContent(r);
		});
	}
	
	public void setNavegador(Navigator nav) {
		navegador=nav;
	}
	
	public boolean iniciarSesion() {
		return i==true;
	}
}