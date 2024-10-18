package persistencia.interfaces;

import java.util.List;
import persistencia.clases.entidades.Producto;

public interface ProductoDAO extends GenericDAO<Producto>{

	public List<Producto> findAllOrderedByName ();
}
