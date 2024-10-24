package Test;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistencia.clases.DAO.EMF;
import persistencia.clases.DAO.MenuDAOHibernateJPA;
import persistencia.clases.entidades.Item;
import persistencia.clases.entidades.Menu;
import persistencia.clases.entidades.MenuVegetariano;
import persistencia.clases.entidades.MenuEstandar;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestMenuDAOHibernateJPA {

    private EntityManager em;
    private MenuDAOHibernateJPA menuDAO;

    @BeforeEach
    void setUp() {
        // Inicializa el EntityManager y el DAO antes de cada prueba
        em = EMF.getEMF().createEntityManager();
        menuDAO = new MenuDAOHibernateJPA();
    }

    @AfterEach
    void tearDown() {
        // Cierra el EntityManager y limpia la base de datos después de cada prueba
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Item").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void testPersistMenu() {
        // Test para persistir un Menu en la base de datos
        Menu menuVegetariano = new MenuVegetariano();
        menuVegetariano.setNombre("Ensalada de verduras");

        em.getTransaction().begin();
        menuDAO.persist(menuVegetariano);
        em.getTransaction().commit();

        // Verificar que el menú se haya persistido correctamente
        Menu menuPersistido = menuDAO.findById(menuVegetariano.getId());
        assertNotNull(menuPersistido);
        assertEquals("Ensalada de verduras", menuPersistido.getNombre());
    }

    @Test
    void testFindVegetarians() {
        // Test para verificar que se devuelvan los menús vegetarianos
        Menu menu1 = new MenuVegetariano();
        menu1.setNombre("Ensalada de frutas");
        
        Menu menu2 = new MenuVegetariano();
        menu2.setNombre("Tarta de espinacas");

        em.getTransaction().begin();
        menuDAO.persist(menu1);
        menuDAO.persist(menu2);
        em.getTransaction().commit();

        List<Menu> menusVegetarianos = menuDAO.findVegetarians();

        assertEquals(2, menusVegetarianos.size());
        assertTrue(menusVegetarianos.stream().anyMatch(m -> m.getNombre().equals("Ensalada de frutas")));
        assertTrue(menusVegetarianos.stream().anyMatch(m -> m.getNombre().equals("Tarta de espinacas")));
    }

    @Test
    void testFindStandards() {
        // Test para verificar que se devuelvan los menús estándar
        Menu menu1 = new MenuEstandar();
        menu1.setNombre("Bife de chorizo");
        
        Menu menu2 = new MenuEstandar();
        menu2.setNombre("Pollo al horno");

        em.getTransaction().begin();
        menuDAO.persist(menu1);
        menuDAO.persist(menu2);
        em.getTransaction().commit();

        List<Menu> menusEstandar = menuDAO.findStandards();

        assertEquals(2, menusEstandar.size());
        assertTrue(menusEstandar.stream().anyMatch(m -> m.getNombre().equals("Bife de chorizo")));
        assertTrue(menusEstandar.stream().anyMatch(m -> m.getNombre().equals("Pollo al horno")));
    }

    @Test
    void testDeleteMenu() {
        // Test para eliminar un Menu de la base de datos
        Menu menu = new MenuVegetariano();
        menu.setNombre("Sopa de verduras");

        em.getTransaction().begin();
        menuDAO.persist(menu);
        em.getTransaction().commit();

        // Eliminar el menú
        em.getTransaction().begin();
        menuDAO.delete(menu.getId());
        em.getTransaction().commit();

        // Verificar que el menú se haya eliminado correctamente
        Menu menuEliminado = menuDAO.findById(menu.getId());
        assertNull(menuEliminado);
    }
}
