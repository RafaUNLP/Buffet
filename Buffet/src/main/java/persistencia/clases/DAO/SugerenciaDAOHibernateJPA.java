package persistencia.clases.DAO;

import java.time.LocalDate;
import java.util.List;

import persistencia.clases.entidades.Sugerencia;
import persistencia.interfaces.SugerenciaDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class SugerenciaDAOHibernateJPA extends GenericDAOHibernateJPA<Sugerencia> implements SugerenciaDAO {

    public SugerenciaDAOHibernateJPA() {
        super(Sugerencia.class);
    }

    private TypedQuery<Sugerencia> findByDateHelper(EntityManager em, LocalDate fecha) {
        return em.createQuery("SELECT s FROM Sugerencia s WHERE s.fecha = :fecha ORDER BY s.fecha DESC", this.entityClass)
                 .setParameter("fecha", fecha);
    }

    @Override
    public List<Sugerencia> findByDate(LocalDate fecha) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return findByDateHelper(em, fecha).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Sugerencia> findByDate(LocalDate fecha, int maxResult) {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return findByDateHelper(em, fecha).setMaxResults(maxResult).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Sugerencia> findAllOrderedByDateAsc() {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Sugerencia s ORDER BY s.fecha ASC", this.entityClass)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Sugerencia> findAllOrderedByDateDesc() {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT s FROM Sugerencia s ORDER BY s.fecha DESC", this.entityClass)
                     .getResultList();
        } finally {
            em.close();
        }
    }
}

