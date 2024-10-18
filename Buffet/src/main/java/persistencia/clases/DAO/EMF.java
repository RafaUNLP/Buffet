package persistencia.clases.DAO;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
	
	private static final EntityManagerFactory em = Persistence.createEntityManagerFactory("miUP");
	
	public static EntityManagerFactory getEMF() {return em;}
}

