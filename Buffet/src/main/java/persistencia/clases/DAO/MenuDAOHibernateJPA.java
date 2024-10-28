package persistencia.clases.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import persistencia.clases.entidades.Menu;
import persistencia.interfaces.MenuDAO;

public class MenuDAOHibernateJPA extends GenericDAOHibernateJPA<Menu> implements MenuDAO{

	public MenuDAOHibernateJPA() {
		super(Menu.class);
	}
	
	@Override
	public List<Menu> findVegetarians() {
		EntityManager em = EMF.getEMF().createEntityManager();
		try {
			return em.createQuery("FROM MenuVegetariano",this.entityClass).getResultList();
		}
		finally {
			em.close();
		}
	}

	@Override
	public List<Menu> findStandards() {
		EntityManager em = EMF.getEMF().createEntityManager();
		try {
			return em.createQuery("FROM MenuEstandar",this.entityClass).getResultList();
		}
		finally {
			em.close();
		}
	}

}
