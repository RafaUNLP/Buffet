package Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import persistencia.clases.DAO.*;
import persistencia.clases.entidades.Turno;


@TestInstance(Lifecycle.PER_CLASS)
public class TestTurnoDAOHibernateJPA {

    private TurnoDAOHibernateJPA turnoDao;
    private EntityManager em;

    @BeforeEach
    public void setUp() throws Exception {
        turnoDao = new TurnoDAOHibernateJPA();
        em = EMF.getEMF().createEntityManager();
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Turno turno1 = new Turno("Mañana", LocalTime.of(8, 0), LocalTime.of(12, 0));
        Turno turno2 = new Turno("Tarde", LocalTime.of(13, 0), LocalTime.of(17, 0));
        Turno turno3 = new Turno("Noche", LocalTime.of(18, 0), LocalTime.of(22, 0));
        em.persist(turno1);
        em.persist(turno2);
        em.persist(turno3);

        tx.commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Turno").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testFindAllOrderedByInitialHour() {
        List<Turno> turnos = turnoDao.findAllOrderedByInitialHour();
        assertNotNull(turnos);
        assertEquals(3, turnos.size());
        
        assertEquals("Mañana", turnos.get(0).getNombre());
        assertEquals("Tarde", turnos.get(1).getNombre());
        assertEquals("Noche", turnos.get(2).getNombre());
    }
}
