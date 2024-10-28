package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import persistencia.clases.DAO.*;
import persistencia.clases.entidades.*;
import persistencia.interfaces.CompraDAO;

@TestInstance(Lifecycle.PER_CLASS)
public class TestCompraDAOHibernateJPA {

    private CompraDAOHibernateJPA compraDao;
    private EntityManager em;
    private Compra compra1,compra2,compra3;

    @BeforeEach
    public void setUp() throws Exception {
        compraDao = new CompraDAOHibernateJPA();
        em = EMF.getEMF().createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Creamos algunas compras de ejemplo
        compra1 = new Compra(500.0, LocalDate.of(2024, 1, 15));
        compra2 = new Compra(1000.0, LocalDate.of(2024, 2, 20));
        compra3 = new Compra(1500.0, LocalDate.of(2024, 3, 10));

        em.persist(compra1);
        em.persist(compra2);
        em.persist(compra3);

        tx.commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Item").executeUpdate();
        em.createQuery("DELETE FROM Compra").executeUpdate();
        em.getTransaction().commit();
        //em.close();
    }

    @Test
    public void testFindBetweenDates() {
    	//caso vacío
    	LocalDate inicio = LocalDate.of(1999, 1, 1);
        LocalDate fin = LocalDate.of(1999, 2, 28);
    	List<Compra> compras = compraDao.findBetweenDates(inicio, fin);
    	assertNotNull(compras);
    	assertTrue(compras.size() == 0);
    	
    	//caso con resultados (hay 3, debería devolver 2)
        inicio = LocalDate.of(2024, 1, 1);
        fin = LocalDate.of(2024, 2, 28);
        compras = compraDao.findBetweenDates(inicio, fin);
        assertEquals(500.0,compras.get(0).getPrecio());
        assertEquals(1000.0,compras.get(1).getPrecio());
        assertEquals(2, compras.size());
    }

    @Test
    public void testFindBetweenDatesWithMaxResults() {
    	//caso vacío
    	LocalDate inicio = LocalDate.of(1999, 1, 1);
        LocalDate fin = LocalDate.of(1999, 2, 28);
    	List<Compra> compras = compraDao.findBetweenDates(inicio, fin, 1);
    	assertNotNull(compras);
    	assertTrue(compras.size() == 0);
    	
    	//caso donde deberia devolver 2 elementos pero se limita a 3
        inicio = LocalDate.of(2024, 1, 1);
        fin = LocalDate.of(2024, 3, 1);
        compras = compraDao.findBetweenDates(inicio, fin, 3);
        assertEquals(500.0,compras.get(0).getPrecio());
        assertEquals(1000.0,compras.get(1).getPrecio());
        assertEquals(2, compras.size());
    	
    	//caso donde deberia devolver los 3 elementos pero se limita a 2
        inicio = LocalDate.of(2024, 1, 1);
        fin = LocalDate.of(2024, 3, 31);
        compras = compraDao.findBetweenDates(inicio, fin, 2);
        assertEquals(500.0,compras.get(0).getPrecio());
        assertEquals(1000.0,compras.get(1).getPrecio());
        assertEquals(2, compras.size());
    }

}
