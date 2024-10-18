package persistencia.clases.entidades;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("administrador")
public class AdministradorRol extends Rol {

	@Override
	public boolean puedeSugerir() {
		return false;
	}

	@Override
	public boolean puedeComprar() {
		return false;
	}

	@Override
	public boolean puedeTernerTurnos() {
		return false;
	}

}