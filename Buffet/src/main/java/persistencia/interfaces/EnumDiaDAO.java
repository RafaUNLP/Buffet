package persistencia.interfaces;

import persistencia.clases.entidades.EnumDia;

public interface EnumDiaDAO extends GenericDAO<EnumDia>{

	public EnumDia findByLiteralValue (String dia); 
}


