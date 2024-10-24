package Test;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistencia.clases.DAO.EMF;
import persistencia.clases.DAO.ProductoDAOHibernateJPA;
import persistencia.clases.entidades.Producto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestProductoDAOHibernateJPA{


    private EntityManager em;
    private ProductoDAOHibernateJPA productoDAO;

    @BeforeEach
    void setUp() {
        // Inicializa el EntityManager y el DAO antes de cada prueba

    	em = EMF.getEMF().createEntityManager();
        productoDAO = new ProductoDAOHibernateJPA();
    }

    @AfterEach
    void tearDown() {
        // Cierra el EntityManager y el EntityManagerFactory después de cada prueba
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Item").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void testPersistProducto() {
        // Test para persistir un Producto en la base de datos
        Producto producto = new Producto();
        producto.setNombre("Cocucha");
        producto.setPrecio(123);

        em.getTransaction().begin();
        productoDAO.persist(producto);
        em.getTransaction().commit();

        // Verificar que el producto se haya persistido correctamente
        Producto productoPersistido = productoDAO.findById(producto.getId());
        assertNotNull(productoPersistido);
        assertEquals("Cocucha", productoPersistido.getNombre());
    }

    @Test
    void testFindAllOrderedByName() {
        // Test para verificar que se devuelvan los productos ordenados por nombre
        Producto producto1 = new Producto("Manzana", 100);
        Producto producto2 = new Producto("Banana", 150);
        Producto producto3 = new Producto("Cereza", 200);

        em.getTransaction().begin();
        productoDAO.persist(producto1);
        productoDAO.persist(producto2);
        productoDAO.persist(producto3);
        em.getTransaction().commit();

        List<Producto> productosOrdenados = productoDAO.findAllOrderedByName();

        assertEquals(3, productosOrdenados.size());
        assertEquals("Banana", productosOrdenados.get(0).getNombre());
        assertEquals("Cereza", productosOrdenados.get(1).getNombre());
        assertEquals("Manzana", productosOrdenados.get(2).getNombre());
    }

    @Test
    void testDeleteProducto() {
        // Test para eliminar un Producto de la base de datos
        Producto producto = new Producto("Pera", 80);

        em.getTransaction().begin();
        productoDAO.persist(producto);
        em.getTransaction().commit();

        // Eliminar el producto
        em.getTransaction().begin();
        productoDAO.delete(producto.getId());
        em.getTransaction().commit();

        // Verificar que el producto se haya eliminado correctamente
        Producto productoEliminado = productoDAO.findById(producto.getId());
        assertNull(productoEliminado);
    }
}