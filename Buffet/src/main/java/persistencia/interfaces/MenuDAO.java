package persistencia.interfaces;

import java.util.List;

import persistencia.clases.entidades.Menu;

public interface MenuDAO extends GenericDAO<Menu>{
	
	public List<Menu> findVegetarians ();
	public List<Menu> findStandards ();
}
