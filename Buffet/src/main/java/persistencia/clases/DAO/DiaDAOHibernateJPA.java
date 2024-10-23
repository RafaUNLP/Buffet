package persistencia.clases.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import persistencia.clases.entidades.Dia;
import persistencia.clases.entidades.EnumDia;
import persistencia.interfaces.DiaDAO;

public class DiaDAOHibernateJPA extends GenericDAOHibernateJPA<Dia> implements DiaDAO{

	public DiaDAOHibernateJPA() {
		super(Dia.class);
	}

	@Override
	public Dia findByEnumDia(EnumDia enumerativo) {
		EntityManager em = EMF.getEMF().createEntityManager();
		Dia buscado= null;
		try{
			buscado = em.createQuery("SELECT d FROM Dia d WHERE d.enumDia = :enumerativo",this.entityClass)
						.setParameter("enumerativo", enumerativo).getSingleResult();
		}
		catch (NoResultException e){
			throw new NoResultException("No se encontró una instancia de Dia que se componga del enum " + enumerativo.toString());
		}
		catch (NonUniqueResultException e){
			throw new NonUniqueResultException("Se encontró más de una instancia de Dia que se compone del enum "
					+ enumerativo.toString() + ", lo cual no debe permitirse");
		}
		catch (Exception e){
			throw e;
		}
		finally {
			em.close();
		}
		return buscado;
	}

}
