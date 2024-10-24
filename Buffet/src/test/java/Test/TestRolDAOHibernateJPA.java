package Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistencia.clases.DAO.EMF;
import persistencia.clases.DAO.RolDAOHibernateJPA;
import persistencia.clases.entidades.*;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestRolDAOHibernateJPA {

    private EntityManager em;
    private RolDAOHibernateJPA rolDAO;

    @BeforeEach
    void setUp() {
        // Inicializa el EntityManager y el DAO antes de cada prueba
        em = EMF.getEMF().createEntityManager();
        rolDAO = new RolDAOHibernateJPA();
    }

    @AfterEach
    void tearDown() {
        // Cierra el EntityManager después de cada prueba
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Rol").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Test
    void testFindByName() {
        // Test para encontrar un rol por su nombre
        Rol rol = new ClienteRol();
        rol.setNombre("cliente");

        em.getTransaction().begin();
        rolDAO.persist(rol);
        em.getTransaction().commit();

        // Buscar el rol por nombre
        Rol rolEncontrado = rolDAO.findByName("cliente");

        assertNotNull(rolEncontrado);
        assertEquals("cliente", rolEncontrado.getNombre());
    }

    @Test
    void testFindByNameNoResult() {
        // Test para cuando no se encuentra ningún rol por el nombre dado
        Rol rolEncontrado = rolDAO.findByName("Inexistente");
        assertNull(rolEncontrado);
    }

    @Test
    void testDeleteRol() {
        // Test para eliminar un Rol de la base de datos
        Rol rol = new ResponsableDeTurnoRol();
        rol.setNombre("responsable_de_turno");

        em.getTransaction().begin();
        rolDAO.persist(rol);
        em.getTransaction().commit();

        // Eliminar el rol
        em.getTransaction().begin();
        rolDAO.delete(rol.getId());
        em.getTransaction().commit();

        // Verificar que el rol se haya eliminado correctamente
        Rol rolEliminado = rolDAO.findById(rol.getId());
        assertNull(rolEliminado);
    }
    
    @Test
    void testPersistRol() {
        // Test para persistir un Rol en la base de datos
        Rol rol = new AdministradorRol();
        rol.setNombre("administrador");

        em.getTransaction().begin();
        rolDAO.persist(rol);
        em.getTransaction().commit();

        // Verificar que el rol se haya persistido correctamente
        Rol rolPersistido = rolDAO.findById(rol.getId());
        assertNotNull(rolPersistido);
        assertEquals("administrador", rolPersistido.getNombre());
    }


}
