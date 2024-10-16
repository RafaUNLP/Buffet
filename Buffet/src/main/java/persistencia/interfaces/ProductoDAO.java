package persistencia.interfaces;

import persistencia.clases.entidades.Producto;

public interface ProductoDAO extends GenericDAO<Producto>{

	public Producto findByName (String nombre);
}
