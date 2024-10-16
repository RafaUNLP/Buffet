package persistencia.interfaces;

import persistencia.clases.entidades.Usuario;
import persistencia.clases.entidades.Rol;

public interface UsuarioDAO extends GenericDAO<Usuario>{

	public Usuario findByEmail (String email);
	public Usuario findByDni (String email);
	public Rol getRol(); //¿esto va????????? ver las N a 1?
}
