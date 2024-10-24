package persistencia.test;

import javax.persistence.Persistence;

import persistencia.clases.DAO.*;
import persistencia.clases.entidades.AdministradorRol;
import persistencia.clases.entidades.ClienteRol;
import persistencia.clases.entidades.Compra;
import persistencia.clases.entidades.MenuEstandar;
import persistencia.clases.entidades.MenuVegetariano;
import persistencia.clases.entidades.Producto;
import persistencia.clases.entidades.Rol;
import persistencia.clases.entidades.Usuario;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Application {


    public static void main(String[] args)  {


        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("miUP");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        
		//modifico el contexto a mi gusto
		
	
        
        MenuVegetariano mv = new MenuVegetariano();
        mv.setNombre("ensalada de ensalada");
        
        MenuEstandar me = new MenuEstandar();
        mv.setNombre("carne de carne");
        
        Producto producto = new Producto();
        producto.setNombre("Cocucha");
        producto.setPrecio(123);
        
        ClienteRol clienterol = new ClienteRol();
        clienterol.setNombre("cliente");
        
        Compra compra1 = new Compra(500.0, LocalDate.of(2024, 1, 15));
        Compra compra2 = new Compra(1000.0, LocalDate.of(2024, 2, 20));
        Compra compra3 = new Compra(1500.0, LocalDate.of(2024, 3, 10));
        compra1.getItem().add(producto);
        

        Rol admin = new AdministradorRol();
        admin.setNombre("administrador");
        Usuario usuario = new Usuario("12345678", "password123", "imagen.jpg", "Juan", "Perez", "juan@example.com");
        usuario.setRol(admin);
        
        RolDAOHibernateJPA rolDAO = new RolDAOHibernateJPA();
        UsuarioDAOHibernateJPA usuarioDAO = new UsuarioDAOHibernateJPA();          

        
        
        entityManager.getTransaction().begin();  
        
  
      
        entityManager.persist(mv);
        entityManager.persist(me);
        entityManager.persist(clienterol);
        entityManager.persist(producto);
        entityManager.persist(compra1);
        
        rolDAO.persist(admin);
        usuarioDAO.persist(usuario);
        
        
        
        entityManager.getTransaction().commit();      
        
        entityManager.close();
        entityManagerFactory.close();
        
      
    }


}



