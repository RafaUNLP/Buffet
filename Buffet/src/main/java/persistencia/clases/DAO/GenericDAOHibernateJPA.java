package persistencia.clases.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import persistencia.interfaces.GenericDAO;

public class GenericDAOHibernateJPA<T> implements GenericDAO<T>{
	 
	protected final Class<T> entityClass;

    public GenericDAOHibernateJPA(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
	@Override
	public T persist(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			tx.commit();
		}
		catch (RuntimeException e) {
			if ( tx != null && tx.isActive()) tx.rollback();
			throw e; // escribir en un log o mostrar un mensaje
		}
		finally {
			em.close();
		}
		return entity;
	}

	@Override
	public boolean exist(long id) {
		EntityManager em = EMF.getEMF().createEntityManager();
	    try {
	        return em.find(entityClass, id) != null;
	    } finally {
	        em.close();
	    }
	}

	@Override
	public T findById(long id) {
		EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
	}

	@Override
	public List<T> findAll() {
		EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
        } finally {
            em.close();
        }
	}

	@Override
	public T update(T entity) {
		EntityManager em= EMF.getEMF().createEntityManager();
		EntityTransaction etx= em.getTransaction();
		etx.begin();
		T entityMerged = em.merge(entity);
		etx.commit();
		em.close();
		return entityMerged;
	}

	@Override
	public void delete(T entity) {
		EntityManager em = EMF.getEMF().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.merge(entity));
			tx.commit();
		}
		catch (RuntimeException e) {
		if ( tx != null && tx.isActive() ) tx.rollback();
			throw e; // escribir en un log o mostrar un mensaje
		}
		finally {
			em.close();
		}
	}

	@Override
	public boolean delete(long id) {
	    EntityManager em = EMF.getEMF().createEntityManager();
	    EntityTransaction tx = null;
	    try {
	        tx = em.getTransaction();
	        tx.begin();
	        T entity = em.find(entityClass, id);
	        if (entity != null) {
	            em.remove(entity);
	            tx.commit();
	            return true;
	        }
	        return false;
	    } catch (RuntimeException e) {
	        if (tx != null && tx.isActive()) tx.rollback();
	        throw e; // escribir en un log o mostrar un mensaje
	    } finally {
	        em.close();
	    }
	}


	
}
