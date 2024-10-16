package persistencia.clases.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class Compra extends EntidadBase{

	/*FALTA RELACIONAR CON ITEMS*/
	@NotNull @DecimalMin(value = "0.0", message = "El precio debe ser al menos 0.0")
    @DecimalMax(value = "999999999.9", message = "El precio no debe ser mayor que 999.999.999,9")
	private double precio;
	
	@NotNull
	private LocalDate fecha;
	
	public Compra () {} //Hibernate y POJOs

	public Compra(double precio,LocalDate fecha) {
		this.precio = precio;
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}
