package persistencia.clases.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import persistencia.clases.entidades.Rol;
import persistencia.clases.entidades.Usuario;
import persistencia.interfaces.UsuarioDAO;

import java.util.List;

public class UsuarioDAOHibernateJPA extends GenericDAOHibernateJPA<Usuario> implements UsuarioDAO {

    public UsuarioDAOHibernateJPA() {
        super(Usuario.class);
    }

    @Override
    public Usuario findByDni(String dni) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni", this.entityClass)
                     .setParameter("dni", dni)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            throw new IllegalStateException("Más de un usuario encontrado con el DNI: " + dni);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Usuario> findByRol(Rol rol) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.rol.nombre = :rol ORDER BY u.nombre ASC", this.entityClass);
            query.setParameter("rol", rol.getNombre());
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<Usuario> findAllOrderedByNameAsc() {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u ORDER BY u.nombre ASC", this.entityClass);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


}
