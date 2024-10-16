package persistencia.interfaces;

import java.util.List;

public interface GenericDAO<T> {

	public T update(T generic);
	public void delete (T generic);
	public boolean delete (long id);
	public boolean exist (long id);
	public void persist (T generic);
	public T find (long id);
	public List<T> findAll();
	
}
