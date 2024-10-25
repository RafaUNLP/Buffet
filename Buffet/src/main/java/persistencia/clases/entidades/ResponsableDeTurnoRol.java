package persistencia.clases.entidades;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("responsable_de_turno")
public class ResponsableDeTurnoRol extends Rol {

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
		return true;
	}

}
