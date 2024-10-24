package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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



@TestInstance(Lifecycle.PER_CLASS)
public class TestCompraDAOHibernateJPA {

    private CompraDAOHibernateJPA compraDao;
    private EntityManager em;

    @BeforeEach
    public void setUp() throws Exception {
        compraDao = new CompraDAOHibernateJPA();
        em = EMF.getEMF().createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Creamos algunas compras de ejemplo
        Compra compra1 = new Compra(500.0, LocalDate.of(2024, 1, 15));
        Compra compra2 = new Compra(1000.0, LocalDate.of(2024, 2, 20));
        Compra compra3 = new Compra(1500.0, LocalDate.of(2024, 3, 10));

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
        em.close();
    }

    @Test
    public void testFindBetweenDates() {
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fin = LocalDate.of(2024, 2, 29);
        
        List<Compra> compras = compraDao.findBetweenDates(inicio, fin);
        assertNotNull(compras);
        assertEquals(2, compras.size());

        assertEquals(500.0, compras.get(0).getPrecio());
        assertEquals(1000.0, compras.get(1).getPrecio());
    }

    @Test
    public void testFindBetweenDatesWithMaxResults() {
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fin = LocalDate.of(2024, 3, 31);
        
        List<Compra> compras = compraDao.findBetweenDates(inicio, fin, 2); // Limitar a 2 resultados
        assertNotNull(compras);
        assertEquals(2, compras.size()); // Aunque hay 3 compras, limitamos a 2

        assertEquals(500.0, compras.get(0).getPrecio());
        assertEquals(1000.0, compras.get(1).getPrecio());
    }

    @Test
    public void testPersistCompraWithItems() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Compra nuevaCompra = new Compra(2000.0, LocalDate.of(2024, 4, 5));
        Item item1 = new Producto("Producto 1", 100.0);
        Item item2 = new Producto("Producto 2", 200.0);
        //Menu item3 = new MenuVegetariano(200.0,"Menu1","ensalada","medallones","flan","agua");
        
        nuevaCompra.getItem().add(item1);
        nuevaCompra.getItem().add(item2);
        //nuevaCompra.getItem().add(item3);
        
        em.persist(item1);
        em.persist(item2);
        em.persist(nuevaCompra);
        tx.commit();

        Compra compraPersistida = em.find(Compra.class, nuevaCompra.getId());
        assertNotNull(compraPersistida);
        assertEquals(2, compraPersistida.getItem().size()); // Verificamos que la compra tiene 3 items
    }
}
