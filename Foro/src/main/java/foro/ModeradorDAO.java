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

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

public class ModeradorDAO {
	public static Moderador loadModeradorByORMID(int attribute) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return loadModeradorByORMID(session, attribute);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador getModeradorByORMID(int attribute) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return getModeradorByORMID(session, attribute);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador loadModeradorByORMID(int attribute, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return loadModeradorByORMID(session, attribute, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador getModeradorByORMID(int attribute, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return getModeradorByORMID(session, attribute, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador loadModeradorByORMID(PersistentSession session, int attribute) throws PersistentException {
		try {
			return (Moderador) session.load(foro.Moderador.class, new Integer(attribute));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador getModeradorByORMID(PersistentSession session, int attribute) throws PersistentException {
		try {
			return (Moderador) session.get(foro.Moderador.class, new Integer(attribute));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador loadModeradorByORMID(PersistentSession session, int attribute, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Moderador) session.load(foro.Moderador.class, new Integer(attribute), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador getModeradorByORMID(PersistentSession session, int attribute, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Moderador) session.get(foro.Moderador.class, new Integer(attribute), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryModerador(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return queryModerador(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryModerador(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return queryModerador(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador[] listModeradorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return listModeradorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador[] listModeradorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return listModeradorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryModerador(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From foro.Moderador as Moderador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryModerador(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From foro.Moderador as Moderador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Moderador", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador[] listModeradorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryModerador(session, condition, orderBy);
			return (Moderador[]) list.toArray(new Moderador[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador[] listModeradorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryModerador(session, condition, orderBy, lockMode);
			return (Moderador[]) list.toArray(new Moderador[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador loadModeradorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return loadModeradorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador loadModeradorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return loadModeradorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador loadModeradorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Moderador[] moderadors = listModeradorByQuery(session, condition, orderBy);
		if (moderadors != null && moderadors.length > 0)
			return moderadors[0];
		else
			return null;
	}
	
	public static Moderador loadModeradorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Moderador[] moderadors = listModeradorByQuery(session, condition, orderBy, lockMode);
		if (moderadors != null && moderadors.length > 0)
			return moderadors[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateModeradorByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return iterateModeradorByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateModeradorByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = foro.CUPersistentManager.instance().getSession();
			return iterateModeradorByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateModeradorByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From foro.Moderador as Moderador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateModeradorByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From foro.Moderador as Moderador");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Moderador", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador createModerador() {
		return new foro.Moderador();
	}
	
	public static boolean save(foro.Moderador moderador) throws PersistentException {
		try {
			foro.CUPersistentManager.instance().saveObject(moderador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean delete(foro.Moderador moderador) throws PersistentException {
		try {
			foro.CUPersistentManager.instance().deleteObject(moderador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(foro.Moderador moderador)throws PersistentException {
		if (moderador instanceof foro.Administrador) {
			return foro.AdministradorDAO.deleteAndDissociate((foro.Administrador) moderador);
		}
		
		try {
			foro.Mensaje[] lBorras = moderador.borra.toArray();
			for(int i = 0; i < lBorras.length; i++) {
				lBorras[i].setEliminado_por(null);
			}
			foro.Usuario[] lEs_amigo_des = moderador.es_amigo_de.toArray();
			for(int i = 0; i < lEs_amigo_des.length; i++) {
				lEs_amigo_des[i].es_su_amigo.remove(moderador);
			}
			foro.Temas[] lCrea_uns = moderador.crea_un.toArray();
			for(int i = 0; i < lCrea_uns.length; i++) {
				lCrea_uns[i].setCreado_por(null);
			}
			foro.Mensaje[] lEscribes = moderador.escribe.toArray();
			for(int i = 0; i < lEscribes.length; i++) {
				lEscribes[i].setPertenece_a(null);
			}
			foro.Usuario[] lReporta_as = moderador.reporta_a.toArray();
			for(int i = 0; i < lReporta_as.length; i++) {
				lReporta_as[i].es_reportado_por.remove(moderador);
			}
			foro.Notificaciones[] lTienes = moderador.tiene.toArray();
			for(int i = 0; i < lTienes.length; i++) {
				lTienes[i].setDe(null);
			}
			foro.Usuario[] lEs_su_amigos = moderador.es_su_amigo.toArray();
			for(int i = 0; i < lEs_su_amigos.length; i++) {
				lEs_su_amigos[i].es_amigo_de.remove(moderador);
			}
			if (moderador.getEliminado_por() != null) {
				moderador.getEliminado_por().elimina.remove(moderador);
			}
			
			foro.Mensaje[] lGustas = moderador.gusta.toArray();
			for(int i = 0; i < lGustas.length; i++) {
				lGustas[i].es_gustado.remove(moderador);
			}
			foro.Usuario[] lEs_reportado_pors = moderador.es_reportado_por.toArray();
			for(int i = 0; i < lEs_reportado_pors.length; i++) {
				lEs_reportado_pors[i].reporta_a.remove(moderador);
			}
			foro.Temas[] lLe_da_me_gustas = moderador.le_da_me_gusta.toArray();
			for(int i = 0; i < lLe_da_me_gustas.length; i++) {
				lLe_da_me_gustas[i].es_gustado.remove(moderador);
			}
			return delete(moderador);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean deleteAndDissociate(foro.Moderador moderador, org.orm.PersistentSession session)throws PersistentException {
		if (moderador instanceof foro.Administrador) {
			return foro.AdministradorDAO.deleteAndDissociate((foro.Administrador) moderador, session);
		}
		
		try {
			foro.Mensaje[] lBorras = moderador.borra.toArray();
			for(int i = 0; i < lBorras.length; i++) {
				lBorras[i].setEliminado_por(null);
			}
			foro.Usuario[] lEs_amigo_des = moderador.es_amigo_de.toArray();
			for(int i = 0; i < lEs_amigo_des.length; i++) {
				lEs_amigo_des[i].es_su_amigo.remove(moderador);
			}
			foro.Temas[] lCrea_uns = moderador.crea_un.toArray();
			for(int i = 0; i < lCrea_uns.length; i++) {
				lCrea_uns[i].setCreado_por(null);
			}
			foro.Mensaje[] lEscribes = moderador.escribe.toArray();
			for(int i = 0; i < lEscribes.length; i++) {
				lEscribes[i].setPertenece_a(null);
			}
			foro.Usuario[] lReporta_as = moderador.reporta_a.toArray();
			for(int i = 0; i < lReporta_as.length; i++) {
				lReporta_as[i].es_reportado_por.remove(moderador);
			}
			foro.Notificaciones[] lTienes = moderador.tiene.toArray();
			for(int i = 0; i < lTienes.length; i++) {
				lTienes[i].setDe(null);
			}
			foro.Usuario[] lEs_su_amigos = moderador.es_su_amigo.toArray();
			for(int i = 0; i < lEs_su_amigos.length; i++) {
				lEs_su_amigos[i].es_amigo_de.remove(moderador);
			}
			if (moderador.getEliminado_por() != null) {
				moderador.getEliminado_por().elimina.remove(moderador);
			}
			
			foro.Mensaje[] lGustas = moderador.gusta.toArray();
			for(int i = 0; i < lGustas.length; i++) {
				lGustas[i].es_gustado.remove(moderador);
			}
			foro.Usuario[] lEs_reportado_pors = moderador.es_reportado_por.toArray();
			for(int i = 0; i < lEs_reportado_pors.length; i++) {
				lEs_reportado_pors[i].reporta_a.remove(moderador);
			}
			foro.Temas[] lLe_da_me_gustas = moderador.le_da_me_gusta.toArray();
			for(int i = 0; i < lLe_da_me_gustas.length; i++) {
				lLe_da_me_gustas[i].es_gustado.remove(moderador);
			}
			try {
				session.delete(moderador);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean refresh(foro.Moderador moderador) throws PersistentException {
		try {
			foro.CUPersistentManager.instance().getSession().refresh(moderador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static boolean evict(foro.Moderador moderador) throws PersistentException {
		try {
			foro.CUPersistentManager.instance().getSession().evict(moderador);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Moderador loadModeradorByCriteria(ModeradorCriteria moderadorCriteria) {
		Moderador[] moderadors = listModeradorByCriteria(moderadorCriteria);
		if(moderadors == null || moderadors.length == 0) {
			return null;
		}
		return moderadors[0];
	}
	
	public static Moderador[] listModeradorByCriteria(ModeradorCriteria moderadorCriteria) {
		return moderadorCriteria.listModerador();
	}
}