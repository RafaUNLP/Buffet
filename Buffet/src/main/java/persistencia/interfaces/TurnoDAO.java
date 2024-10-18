package persistencia.interfaces;

import java.util.List;

import persistencia.clases.entidades.Turno;

public interface TurnoDAO extends GenericDAO<Turno> {

	public List<Turno> findAllOrderedByInitialHour();
}
