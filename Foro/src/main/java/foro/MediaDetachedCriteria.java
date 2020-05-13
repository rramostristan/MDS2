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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class MediaDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression attribute;
	public final IntegerExpression ID;
	public final StringExpression enlace;
	public final IntegerExpression es_deId;
	public final AssociationExpression es_de;
	public final IntegerExpression id_media;
	
	public MediaDetachedCriteria() {
		super(foro.Media.class, foro.MediaCriteria.class);
		attribute = new IntegerExpression("attribute", this.getDetachedCriteria());
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		enlace = new StringExpression("enlace", this.getDetachedCriteria());
		es_deId = new IntegerExpression("es_de.attribute", this.getDetachedCriteria());
		es_de = new AssociationExpression("es_de", this.getDetachedCriteria());
		id_media = new IntegerExpression("id_media", this.getDetachedCriteria());
	}
	
	public MediaDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, foro.MediaCriteria.class);
		attribute = new IntegerExpression("attribute", this.getDetachedCriteria());
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		enlace = new StringExpression("enlace", this.getDetachedCriteria());
		es_deId = new IntegerExpression("es_de.attribute", this.getDetachedCriteria());
		es_de = new AssociationExpression("es_de", this.getDetachedCriteria());
		id_media = new IntegerExpression("id_media", this.getDetachedCriteria());
	}
	
	public MensajeDetachedCriteria createEs_deCriteria() {
		return new MensajeDetachedCriteria(createCriteria("es_de"));
	}
	
	public Media uniqueMedia(PersistentSession session) {
		return (Media) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Media[] listMedia(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Media[]) list.toArray(new Media[list.size()]);
	}
}
