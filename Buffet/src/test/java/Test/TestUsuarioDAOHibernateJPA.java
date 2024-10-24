package Test;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistencia.clases.DAO.EMF;
import persistencia.clases.DAO.UsuarioDAOHibernateJPA;
import persistencia.clases.entidades.*; // Subclase concreta de Rol
import persistencia.clases.entidades.Rol;
import persistencia.clases.entidades.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestUsuarioDAOHibernateJPA {

    private EntityManager em;
    private UsuarioDAOHibernateJPA usuarioDAO;

    @BeforeEach
    void setUp() {
        em = EMF.getEMF().createEntityManager();
        usuarioDAO = new UsuarioDAOHibernateJPA();
    }

    @AfterEach
    void tearDown() {
        // Limpia la base de datos tras cada prueba
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.createQuery("DELETE FROM Usuario").executeUpdate();
        em.createQuery("DELETE FROM Rol").executeUpdate();
        tx.commit();
        em.close();
    }

    @Test
    void testPersistUsuario() {
        // Crear un rol y usuario y persistirlos
        Rol admin = new AdministradorRol();
        admin.setNombre("administrador");
        Usuario usuario = new Usuario("12345678", "password123", "imagen.jpg", "Juan", "Perez", "juan@example.com");
        usuario.setRol(admin);

        em.getTransaction().begin();
        em.persist(admin);
        usuarioDAO.persist(usuario);
        em.getTransaction().commit();

        // Verificar que el usuario fue persistido correctamente
        Usuario usuarioPersistido = usuarioDAO.findById(usuario.getId());
        assertNotNull(usuarioPersistido);
        assertEquals("12345678", usuarioPersistido.getDni());
        assertEquals("Juan", usuarioPersistido.getNombre());
        assertEquals("Perez", usuarioPersistido.getApellido());
    }

    @Test
    void testFindByDni() {
        // Crear y persistir un usuario
        Rol admin = new AdministradorRol();
        admin.setNombre("administrador");
        Usuario usuario = new Usuario("87654321", "password321", "imagen2.jpg", "Maria", "Lopez", "maria@example.com");
        usuario.setRol(admin);

        em.getTransaction().begin();
        em.persist(admin);
        usuarioDAO.persist(usuario);
        em.getTransaction().commit();

        // Verificar que el usuario se puede encontrar por DNI
        Usuario usuarioEncontrado = usuarioDAO.findByDni("87654321");
        assertNotNull(usuarioEncontrado);
        assertEquals("Maria", usuarioEncontrado.getNombre());
        assertEquals("Lopez", usuarioEncontrado.getApellido());
    }

    @Test
    void testFindByDniNoResult() {
        // Verificar que buscar un DNI inexistente devuelve null
        Usuario usuarioNoEncontrado = usuarioDAO.findByDni("99999999");
        assertNull(usuarioNoEncontrado);
    }

    @Test
    void testFindByRol() {
        // Crear y persistir varios usuarios con diferentes roles
        Rol admin = new AdministradorRol();
        admin.setNombre("administrador");
        Rol cliente = new ClienteRol();
        cliente.setNombre("cliente");
        Usuario usuario1 = new Usuario("12345678", "password123", "imagen.jpg", "Juan", "Perez", "juan@example.com");
        Usuario usuario2 = new Usuario("87654321", "password321", "imagen2.jpg", "Maria", "Lopez", "maria@example.com");
        usuario1.setRol(admin);
        usuario2.setRol(cliente);

        em.getTransaction().begin();
        em.persist(admin);
        em.persist(cliente);
        usuarioDAO.persist(usuario1);
        usuarioDAO.persist(usuario2);
        em.getTransaction().commit();

        // Buscar usuarios por rol 'Administrador'
        List<Usuario> usuariosAdmin = usuarioDAO.findByRol(admin);
        assertEquals(1, usuariosAdmin.size());
        assertEquals("Juan", usuariosAdmin.get(0).getNombre());

        // Buscar usuarios por rol 'Cliente'
        List<Usuario> usuariosCliente = usuarioDAO.findByRol(cliente);
        assertEquals(1, usuariosCliente.size());
        assertEquals("Maria", usuariosCliente.get(0).getNombre());
    }

    @Test
    void testFindAllOrderedByNameAsc() {
        Rol admin = new AdministradorRol();
        admin.setNombre("administrador");
        Usuario usuario1 = new Usuario("12345678", "password123", "imagen.jpg", "Juan", "Perez", "juan@example.com");
        Usuario usuario2 = new Usuario("87654321", "password321", "imagen2.jpg", "Maria", "Lopez", "maria@example.com");
        Usuario usuario3 = new Usuario("11223344", "password789", "imagen3.jpg", "Ana", "Diaz", "ana@example.com");
        usuario1.setRol(admin);
        usuario2.setRol(admin);
        usuario3.setRol(admin);

        em.getTransaction().begin();
        em.persist(admin);
        usuarioDAO.persist(usuario1);
        usuarioDAO.persist(usuario2);
        usuarioDAO.persist(usuario3);
        em.getTransaction().commit();

        // Buscar todos los usuarios ordenados por nombre ascendente
        List<Usuario> usuariosOrdenados = usuarioDAO.findAllOrderedByNameAsc();
        assertEquals(3, usuariosOrdenados.size());
        assertEquals("Ana", usuariosOrdenados.get(0).getNombre());
        assertEquals("Juan", usuariosOrdenados.get(1).getNombre());
        assertEquals("Maria", usuariosOrdenados.get(2).getNombre());
    }

    @Test
    void testDeleteUsuario() {
        // Crear y persistir un usuario
        Rol admin = new AdministradorRol();
        admin.setNombre("administrador");
        Usuario usuario = new Usuario("12345678", "password123", "imagen.jpg", "Juan", "Perez", "juan@example.com");
        usuario.setRol(admin);

        em.getTransaction().begin();
        em.persist(admin);
        usuarioDAO.persist(usuario);
        em.getTransaction().commit();

        // Eliminar el usuario
        em.getTransaction().begin();
        usuarioDAO.delete(usuario.getId());
        em.getTransaction().commit();

        // Verificar que el usuario fue eliminado
        Usuario usuarioEliminado = usuarioDAO.findById(usuario.getId());
        assertNull(usuarioEliminado);
    }
}
