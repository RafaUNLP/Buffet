package persistencia.interfaces;

import persistencia.clases.entidades.Turno;

public interface TurnoDAO extends GenericDAO<Turno> {

	public Turno findByName (String nombre);
}
