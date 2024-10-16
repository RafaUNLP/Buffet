package persistencia.clases.entidades;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Rol extends EntidadBase{
	
	@NotNull @Size(max=30,message="El nombre no debe superar los 30 caracteres")
	private String nombre;
	
	public Rol() {} //Hibernate y POJOs
	
	public Rol(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean equals(Rol otro) { /*para que no importe si son o no la misma ref de memoria*/
		return this.nombre.toLowerCase().trim().equals(otro.getNombre().toLowerCase().trim());
	}

}
