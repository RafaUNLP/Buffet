package Test;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import persistencia.clases.DAO.*;
import persistencia.clases.entidades.*;


@TestInstance(Lifecycle.PER_CLASS)
public class TestSugereciaDAOHibernateJPA {

    private SugerenciaDAOHibernateJPA sugerenciaDAO;
    private EntityManager em;

    @BeforeEach
    public void setUp() throws Exception {
        sugerenciaDAO = new SugerenciaDAOHibernateJPA();
        em = EMF.getEMF().createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Creamos y persistimos sugerencias de ejemplo
        Sugerencia sugerencia1 = new Sugerencia("Texto de ejemplo 1", LocalDate.of(2024, 10, 23));
        Sugerencia sugerencia2 = new Sugerencia("Texto de ejemplo 2", LocalDate.of(2024, 10, 22));
        Sugerencia sugerencia3 = new Sugerencia("Texto de ejemplo 3", LocalDate.of(2024, 10, 22));
        em.persist(sugerencia1);
        em.persist(sugerencia2);
        em.persist(sugerencia3);

        tx.commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Sugerencia").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testFindByDate() {
        List<Sugerencia> sugerencias = sugerenciaDAO.findByDate(LocalDate.of(2024, 10, 23));
        assertNotNull(sugerencias);
        assertEquals(1, sugerencias.size());
        assertEquals("Texto de ejemplo 1", sugerencias.get(0).getTexto());
    }

    @Test
    public void testFindByDateWithLimit() {
        List<Sugerencia> sugerencias = sugerenciaDAO.findByDate(LocalDate.of(2024, 10, 23), 1);
        assertNotNull(sugerencias);
        assertEquals(1, sugerencias.size());
    }

    @Test
    public void testFindAllOrderedByDateAsc() {

        List<Sugerencia> sugerencias = sugerenciaDAO.findAllOrderedByDateAsc();
        assertNotNull(sugerencias);
        assertEquals(3, sugerencias.size());
        assertEquals("Texto de ejemplo 2", sugerencias.get(0).getTexto()); // Debe estar en orden ascendente fecha exacta ordena por texto CREO
    }

    @Test
    public void testFindAllOrderedByDateDesc() {

        List<Sugerencia> sugerencias = sugerenciaDAO.findAllOrderedByDateDesc();
        assertNotNull(sugerencias);
        assertEquals(3, sugerencias.size());
        assertEquals("Texto de ejemplo 1", sugerencias.get(0).getTexto()); // Debe estar en orden descendente
    }

    @Test
    public void testPersist() {
        Sugerencia nuevaSugerencia = new Sugerencia("Texto de ejemplo 3", LocalDate.of(2024, 10, 24));
        sugerenciaDAO.persist(nuevaSugerencia);

        List<Sugerencia> sugerencias = sugerenciaDAO.findByDate(LocalDate.of(2024, 10, 24));
        assertNotNull(sugerencias);
        assertEquals(1, sugerencias.size());
        assertEquals("Texto de ejemplo 3", sugerencias.get(0).getTexto());
    }

    @Test
    public void testActualizar() {
        Sugerencia sugerencia = sugerenciaDAO.findByDate(LocalDate.of(2024, 10, 23)).get(0);
        sugerencia.setTexto("Texto actualizado");
        sugerenciaDAO.update(sugerencia);

        Sugerencia actualizada = sugerenciaDAO.findByDate(LocalDate.of(2024, 10, 23)).get(0);
        assertEquals("Texto actualizado", actualizada.getTexto());
    }

    @Test
    public void testBorrar() {
        Sugerencia sugerencia = sugerenciaDAO.findByDate(LocalDate.of(2024, 10, 23)).get(0);
        assertNotNull(sugerencia);

        sugerenciaDAO.delete(sugerencia);
        List<Sugerencia> sugerencias = sugerenciaDAO.findByDate(LocalDate.of(2024, 10, 23));
        assertTrue(sugerencias.isEmpty());
    }
}
