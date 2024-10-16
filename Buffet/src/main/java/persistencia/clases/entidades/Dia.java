package persistencia.clases.entidades;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Dia extends EntidadBase{

	@Enumerated(EnumType.STRING)
	private EnumDia enumDia;
	
	public Dia() {} //Hibernate y POJOs


	public Dia(EnumDia enumDia) {
		this.enumDia = enumDia;
	}
	
	/*FALTAN RELACIONES*/
	
	
}
