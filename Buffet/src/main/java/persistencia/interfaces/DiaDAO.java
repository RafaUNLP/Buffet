package persistencia.interfaces;

import persistencia.clases.entidades.Dia;
import persistencia.clases.entidades.EnumDia;

public interface DiaDAO extends GenericDAO<Dia>{

	public Dia findByEnumDia (EnumDia enumerativo); 
}
