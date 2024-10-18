package persistencia.interfaces;

import persistencia.clases.entidades.Usuario;
import java.util.List;
import persistencia.clases.entidades.Rol;

public interface UsuarioDAO extends GenericDAO<Usuario>{

	public Usuario findByDni (String dni);
	public List<Usuario> findByRol(Rol rol);
	List<Usuario> findAllOrderedByNameAsc();
	
}
