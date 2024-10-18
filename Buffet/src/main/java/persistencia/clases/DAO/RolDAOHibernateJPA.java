package persistencia.clases.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import persistencia.clases.entidades.Rol;
import persistencia.interfaces.RolDAO;

public class RolDAOHibernateJPA extends GenericDAOHibernateJPA<Rol> implements RolDAO{

	public RolDAOHibernateJPA() {
		super(Rol.class);
	}

	@Override
	public Rol findByName(String nombre) {
	    EntityManager em = EMF.getEMF().createEntityManager();
	    try {
	        return em.createQuery("SELECT r FROM Rol r WHERE r.nombre = :nombre", this.entityClass)
	        		.setParameter("nombre", nombre).getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    } catch (NonUniqueResultException e) {
	        throw new IllegalStateException("Más de un rol encontrado con el nombre: " + nombre);
	    } finally {
	        em.close();
	    }
	}

}
