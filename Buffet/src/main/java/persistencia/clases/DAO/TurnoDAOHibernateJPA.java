package persistencia.clases.DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import persistencia.clases.entidades.Turno;
import persistencia.interfaces.TurnoDAO;

public class TurnoDAOHibernateJPA extends GenericDAOHibernateJPA<Turno> implements TurnoDAO{

	public TurnoDAOHibernateJPA() {
		super(Turno.class);
	}

	@Override
    public List<Turno> findAllOrderedByInitialHour() {
        EntityManager em = EMF.getEMF().createEntityManager();
        try {
            return em.createQuery("SELECT t FROM Turno t ORDER BY t.horaEntrada ASC", Turno.class).getResultList();
        } finally {
            em.close();
        }
    }
}
