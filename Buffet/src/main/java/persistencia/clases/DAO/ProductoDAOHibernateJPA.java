package persistencia.clases.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import persistencia.clases.entidades.Producto;
import persistencia.interfaces.ProductoDAO;

public class ProductoDAOHibernateJPA extends GenericDAOHibernateJPA<Producto> implements ProductoDAO {

	public ProductoDAOHibernateJPA() {
		super(Producto.class);
	}

	@Override
	public List<Producto> findAllOrderedByName() {
		EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Producto p ORDER BY p.nombre", this.entityClass).getResultList();
        } finally {
            em.close();
        }
	}

}
