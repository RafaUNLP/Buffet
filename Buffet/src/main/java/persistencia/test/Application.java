package persistencia.test;

import javax.persistence.Persistence;

import persistencia.clases.entidades.ClienteRol;
import persistencia.clases.entidades.Compra;
import persistencia.clases.entidades.Item;
import persistencia.clases.entidades.MenuEstandar;
import persistencia.clases.entidades.MenuVegetariano;
import persistencia.clases.entidades.Producto;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Application {


    public static void main(String[] args)  {


        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("miUP");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        
		//modifico el contexto a mi gusto
		
	
        Customer customer = new Customer();
        customer.setFirstName("Dennys");
        customer.setLastName("Fredericci");
        
        MenuVegetariano mv = new MenuVegetariano();
        mv.setNombre("ensalada de ensalada");
        
        MenuEstandar me = new MenuEstandar();
        mv.setNombre("carne de carne");
        
        Producto producto = new Producto();
        producto.setNombre("Cocucha");
        producto.setPrecio(123);
        
        ClienteRol clienterol = new ClienteRol();
        
        Compra compra1 = new Compra(500.0, LocalDate.of(2024, 1, 15));
        Compra compra2 = new Compra(1000.0, LocalDate.of(2024, 2, 20));
        Compra compra3 = new Compra(1500.0, LocalDate.of(2024, 3, 10));
        
        compra1.getItem().add(producto);

        
        
        entityManager.getTransaction().begin();  
        
  
        entityManager.persist(customer);       
        entityManager.persist(mv);
        entityManager.persist(me);
        entityManager.persist(clienterol);
        entityManager.persist(producto);
        entityManager.persist(compra1);
        
        
        
        
        entityManager.getTransaction().commit();      
        
        entityManager.close();
        entityManagerFactory.close();
        
      
    }


}



