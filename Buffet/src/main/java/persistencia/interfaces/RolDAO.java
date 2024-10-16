package persistencia.interfaces;

import persistencia.clases.entidades.Rol;

public interface RolDAO extends GenericDAO<Rol> {

	public Rol findByName(String nombre);
}
