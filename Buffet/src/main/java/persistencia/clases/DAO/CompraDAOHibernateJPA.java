package persistencia.clases.DAO;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import persistencia.clases.entidades.Compra;
import persistencia.interfaces.CompraDAO;

public class CompraDAOHibernateJPA extends GenericDAOHibernateJPA<Compra> implements CompraDAO{

	public CompraDAOHibernateJPA() {
		super(Compra.class);
	}
	
	private TypedQuery<Compra> findBetweenHelper (EntityManager em,LocalDate inicio, LocalDate fin){
		TypedQuery<Compra> query = em.createQuery("SELECT c FROM Compra c WHERE c.fecha >= :inicio AND c.fecha <= :fin", this.entityClass).
				setParameter("inicio", inicio).setParameter("fin", fin);
		return query;
	}

	@Override
	public List<Compra> findBetweenDates(LocalDate inicio, LocalDate fin) {
		EntityManager em = EMF.getEMF().createEntityManager();
		List<Compra> compras = this.findBetweenHelper(em, inicio, fin).getResultList();
		em.close();
		return compras;
		
	}

	@Override
	public List<Compra> findBetweenDates(LocalDate inicio, LocalDate fin, int maxResultados) {
		EntityManager em = EMF.getEMF().createEntityManager();
		List<Compra> compras = this.findBetweenHelper(em, inicio, fin).setMaxResults(maxResultados).getResultList();
		em.close();
		return compras;
	}

}