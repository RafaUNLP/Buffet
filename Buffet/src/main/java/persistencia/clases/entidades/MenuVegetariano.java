package persistencia.clases.entidades;

import jakarta.persistence.DiscriminatorValue;

@DiscriminatorValue("vegetariano")
public class MenuVegetariano extends Menu {

	/*usamos MenuVegetariano y MenuEstandar para forzar mediante el tipado que
	 * un dia tenga si o si un menu de cada tipo*/
	
	public MenuVegetariano () { super();}
	
	public MenuVegetariano(double precio, String nombre, String entrada, String platoPrincipal, String postre, String bebida) {
		super(precio, nombre, entrada, platoPrincipal, postre, bebida);
	}

	@Override
	public boolean esVegetariano() {
		return true;
	}
}
