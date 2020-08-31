/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Ramón Ramos(University of Almeria)
 * License Type: Academic
 */
package foro;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Foto")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorValue("Foto")
@PrimaryKeyJoinColumn(name="MediaId_media", referencedColumnName="Id_media")
public class Foto extends foro.Media implements Serializable {
	public Foto() {
	}
	
	@Column(name="Enlace", nullable=true, length=255)	
	private String enlace;
	
	public void setEnlace(String value) {
		this.enlace = value;
	}
	
	public String getEnlace() {
		return enlace;
	}
	
	public String toString() {
		return super.toString();
	}
	
}
