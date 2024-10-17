package persistencia.clases.entidades;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Item extends EntidadBase{

	 /*Esta clase inicialmente era una interfaz, pero como Hibernate no las persiste, me vi obligado a hacerlo 
	 * una clase abstracta para que una Compra pueda tener una lista polimórfica de Items.
	 
	 * NOTA: @MappedSuperclass permite la herencia pero no persiste la clase*/
	
}

