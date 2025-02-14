package persistencia.clases.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
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
