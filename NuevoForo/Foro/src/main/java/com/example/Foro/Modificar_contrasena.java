package com.example.Foro;

import org.orm.PersistentException;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import basededatos.BD_Principal;
import basededatos.UsuarioDAO;
import com.vaadin.ui.Button.ClickEvent;

public class Modificar_contrasena extends Modificar_contrasena_ventana implements View {
	
	Button aceptar;
	PasswordField ncontrasena2,acontrasena,ncontrasena;
	Navigator navegador;
	BD_Principal bd;
	MyUI m;
	boolean i;
	
	public Modificar_contrasena() {
		
	}

	public Modificar_contrasena(MyUI myui) {
		// TODO Auto-generated constructor stub
		m=myui;
	}

	public void inicio(int idusuario) {
		bd = new BD_Principal();
		acontrasena=this.acontrasena_html;
		ncontrasena=this.ncontrasena_html;
		ncontrasena2=this.ncontrasena2_html;
		aceptar=this.aceptar_html;
		aceptar.addClickListener(e-> {
				try {
					basededatos.Usuario user = UsuarioDAO.getUsuarioByORMID(idusuario);
					if(acontrasena.getValue().equals(user.getContrasena()) && ncontrasena.getValue().equals(ncontrasena2.getValue())) {
						user.setContrasena(ncontrasena.getValue());
						UsuarioDAO.save(user);
						i=true;
						
					}
					if(bd.listaAdministradores().contains(idusuario)) {
							Ajustes_administrador ajustes =  new Ajustes_administrador();
							ajustes.inicio(idusuario,1);
							m.setContent(ajustes);
					}
					else if(!bd.listaAdministradores().contains(idusuario) && bd.listaModeradores().contains(idusuario)) {
							Ajustes_moderador ajustes =  new Ajustes_moderador();
							ajustes.inicio(idusuario,1);
							m.setContent(ajustes);
					}
					else {
							Ajustes ajustes =  new Ajustes();
							ajustes.inicio(idusuario,1);
							m.setContent(ajustes);
						}
				} catch (PersistentException i) {
					// TODO Auto-generated catch block
					i.printStackTrace();
				}
		});
	}
	
	public void setNavegador(Navigator nav) {
		navegador=nav;
	}
	
	public boolean modificar_Contrasena() {
		return i==true;
	}
}