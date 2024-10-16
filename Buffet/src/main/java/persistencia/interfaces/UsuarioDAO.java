package persistencia.interfaces;

import persistencia.clases.entidades.Usuario;
import java.util.List;
import persistencia.clases.entidades.Rol;

public interface UsuarioDAO extends GenericDAO<Usuario>{

	public Usuario findByEmail (String email);
	public Usuario findByDni (String dni);
	public List<Usuario> findByRol(Rol rol);
	public Rol getRol(); //¿esto va????????? ver las N a 1?
}
