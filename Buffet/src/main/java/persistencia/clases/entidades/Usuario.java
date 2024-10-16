package persistencia.clases.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;

public class Usuario extends EntidadBase{

	
	@NotNull @Size(min=8,max=8,message="El dni debe contar con 8 caracteres numéricos")
	private String dni;/*conviene que el dni sea un string para validar mejor y poder tener ceros a la izq*/
	
	@NotNull @Size(min=8,message="La contraseña debe contar con al menos 8 caracteres")
	private String password;
	
	@NotNull
	private String pathImagen;
	
	@NotNull @Size(max=30,message="El nombre no debe superar los 30 caracteres")
	private String nombre;
	
	@NotNull @Size(max=30,message="El apellido no debe superar los 30 caracteres")
	private String apellido;
	
    @NotNull @Email(message = "Correo electrónico inválido")
	private String email;
    
    /*FALTAN LAS RELACIONES*/
	
	public Usuario() {} //lo requiere Hibernate, que espera POJOs

	public Usuario(String dni, String password, String pathImagen, String nombre, String apellido, String email) {
		this.dni = dni;
		this.password = password;
		this.pathImagen = pathImagen;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagen() {
		return pathImagen;
	}

	public void setImagen(String pathImagen) {
		this.pathImagen = pathImagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}
	
	
}
