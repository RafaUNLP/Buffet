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
			return em.createQuery("SELECT m FROM Menu WHERE m.tipo_menu = vegetariano",this.entityClass).getResultList();
		}
		finally {
			em.close();
		}
	}

	@Override
	public List<Menu> findStandards() {
		EntityManager em = EMF.getEMF().createEntityManager();
		try {
			return em.createQuery("SELECT m FROM Menu WHERE m.tipo_menu = estandar",this.entityClass).getResultList();
		}
		finally {
			em.close();
		}
	}

}
