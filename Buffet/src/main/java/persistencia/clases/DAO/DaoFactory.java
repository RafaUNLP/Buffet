package persistencia.clases.DAO;

import persistencia.interfaces.*;

public class DaoFactory {

	public class DAOFactory {

	    public UsuarioDAO getUsuarioDAO() {
	        return new UsuarioDAOHibernateJPA();
	    }

	    public TurnoDAO getTurnoDAO() {
	        return new TurnoDAOHibernateJPA();
	    }

	    public SugerenciaDAO getSugerenciaDAO() {
	        return new SugerenciaDAOHibernateJPA();
	    }

	    public RolDAO getRolDAO() {
	        return new RolDAOHibernateJPA();
	    }

	    public ProductoDAO getProductoDAO() {
	        return new ProductoDAOHibernateJPA();
	    }

	    public MenuDAO getMenuDAO() {
	        return new MenuDAOHibernateJPA();
	    }

	    public DiaDAO getDiaDAO() {
	        return new DiaDAOHibernateJPA();
	    }

	    public CompraDAO getCompraDAO() {
	        return new CompraDAOHibernateJPA();
	    }
	}

	
}
