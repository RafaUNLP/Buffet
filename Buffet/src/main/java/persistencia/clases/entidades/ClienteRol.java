package persistencia.clases.entidades;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("cliente")
public class ClienteRol extends Rol {

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
