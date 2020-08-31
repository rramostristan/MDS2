/**
 * Licensee: Claudio(University of Almeria)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateClasesIUData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = paquete1.ClasesIUPersistentManager.instance().getSession().beginTransaction();
		try {
			paquete1.Seccion lpaquete1Seccion = paquete1.SeccionDAO.createSeccion();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : tema_hijo
			paquete1.SeccionDAO.save(lpaquete1Seccion);
			paquete1.Usuario lpaquete1Usuario = paquete1.UsuarioDAO.createUsuario();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : amigo_de, le_gusta, administrado, es_moderado, mistad_con
			paquete1.UsuarioDAO.save(lpaquete1Usuario);
			paquete1.Administrador lpaquete1Administrador = paquete1.AdministradorDAO.createAdministrador();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : administra
			paquete1.AdministradorDAO.save(lpaquete1Administrador);
			paquete1.Tema lpaquete1Tema = paquete1.TemaDAO.createTema();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : tema_contiene, pertenece_a
			paquete1.TemaDAO.save(lpaquete1Tema);
			paquete1.Nota lpaquete1Nota = paquete1.NotaDAO.createNota();
			// Initialize the properties of the persistent object here
			paquete1.NotaDAO.save(lpaquete1Nota);
			paquete1.Media lpaquete1Media = paquete1.MediaDAO.createMedia();
			// Initialize the properties of the persistent object here
			paquete1.MediaDAO.save(lpaquete1Media);
			paquete1.Foto lpaquete1Foto = paquete1.FotoDAO.createFoto();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : foto_pertenece_a_mensaje
			paquete1.FotoDAO.save(lpaquete1Foto);
			paquete1.Video lpaquete1Video = paquete1.VideoDAO.createVideo();
			// Initialize the properties of the persistent object here
			paquete1.VideoDAO.save(lpaquete1Video);
			paquete1.Mensaje lpaquete1Mensaje = paquete1.MensajeDAO.createMensaje();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : es_gustado, es_referenciado, mensaje_tiene_imagen, Respuesta_de, tema_padre, mensaje_inicial
			paquete1.MensajeDAO.save(lpaquete1Mensaje);
			paquete1.Moderador lpaquete1Moderador = paquete1.ModeradorDAO.createModerador();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : modera
			paquete1.ModeradorDAO.save(lpaquete1Moderador);
			paquete1.Notificacion lpaquete1Notificacion = paquete1.NotificacionDAO.createNotificacion();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : referencia_a_mensaje
			paquete1.NotificacionDAO.save(lpaquete1Notificacion);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateClasesIUData createClasesIUData = new CreateClasesIUData();
			try {
				createClasesIUData.createTestData();
			}
			finally {
				paquete1.ClasesIUPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
