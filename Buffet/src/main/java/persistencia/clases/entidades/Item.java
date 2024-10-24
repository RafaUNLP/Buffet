package persistencia.clases.entidades;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;


@Entity
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Item extends EntidadBase{

	 /*Esta clase inicialmente era una interfaz, pero como Hibernate no las persiste, me vi obligado a hacerlo 
	 * una clase abstracta para que una Compra pueda tener una lista polimórfica de Items.
	 
	 * NOTA: @MappedSuperclass permite la herencia pero no persiste la clase*/
	//@ManyToOne
	//@JoinColumn(name = "compra_id")
	//private Compra compra;
}

