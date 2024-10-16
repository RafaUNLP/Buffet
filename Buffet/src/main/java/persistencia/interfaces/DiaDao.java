package persistencia.interfaces;

import persistencia.clases.entidades.Dia;
import persistencia.clases.entidades.EnumDia;

public interface DiaDao extends GenericDAO<Dia>{

	public Dia findByEnumDia (EnumDia enumerativo); 
}
