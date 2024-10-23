package persistencia.test;

import javax.persistence.Persistence;

import persistencia.clases.entidades.Producto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Application {


    public static void main(String[] args)  {


        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("miUP");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        

		
		//modifico el contexto a mi gusto
		
	
        Customer customer = new Customer();
        Producto producto = new Producto();
        producto.setNombre("Cocucha");
        producto.setPrecio(123);
        customer.setFirstName("Dennys");
        customer.setLastName("Fredericci");
        System.out.println("entra");
        
        entityManager.getTransaction().begin();
        
  
        entityManager.persist(customer);
        entityManager.persist(producto);
        
        entityManager.getTransaction().commit();


        
        entityManager.close();
        entityManagerFactory.close();
        
      
    }


}



