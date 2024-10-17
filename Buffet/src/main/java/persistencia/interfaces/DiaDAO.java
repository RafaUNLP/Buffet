package persistencia.interfaces;

import persistencia.clases.entidades.Dia;
import persistencia.clases.entidades.EnumDia;


/*existia un EnumDiaDAO pero como al final no se persisten, se sacó*/
public interface DiaDAO extends GenericDAO<Dia>{

	public Dia findByEnumDia (EnumDia enumerativo); 
}
