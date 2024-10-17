package persistencia.clases.entidades;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("cliente")
public class CienteRol extends Rol {

	@Override
	public boolean puedeSugerir() {
		return true;
	}

	@Override
	public boolean puedeComprar() {
		return true;
	}

	@Override
	public boolean puedeTernerTurnos() {
		return false;
	}

}
