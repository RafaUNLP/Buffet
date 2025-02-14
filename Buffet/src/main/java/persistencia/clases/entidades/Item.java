package persistencia.clases.entidades;


import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item extends EntidadBase{

	 /*Esta clase inicialmente era una interfaz, pero como Hibernate no las persiste, me vi obligado a hacerlo 
	 * una clase abstracta para que una Compra pueda tener una lista polimórfica de Items.
	 */
	public abstract String getNombre();
	public abstract void setNombre (String nombre);
	
	public abstract double getPrecio();
	public abstract void setPrecio (double precio);
}

