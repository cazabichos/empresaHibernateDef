package repositories.departamentos;

import java.util.List;
import java.util.Optional;



import db.HibernateManager;
import exceptions.DepartamentoException;
import jakarta.persistence.TypedQuery;
import models.Departamento;

public class DepartamentosRepositoryImpl implements DepartamentosRepository {
	
	

	@Override
	public List<Departamento> findAll() {
		 HibernateManager hb = HibernateManager.getInstance();
		    hb.open();
		    hb.getTransaction().begin();

		    try {
		        TypedQuery<Departamento> query = hb.getManager().createQuery("SELECT d FROM Departamento d", Departamento.class);
		        List<Departamento> departamentos = query.getResultList();

		        hb.getTransaction().commit();
		        return departamentos;
		    } catch (Exception e) {
		        if (hb.getTransaction().isActive()) {
		            hb.getTransaction().rollback();
		        }
		        throw e;
		    } finally {
		        hb.close();
		    }
	}

	@Override
	public Optional<Departamento> findById(Integer id) {
		HibernateManager hb = HibernateManager.getInstance();
	    hb.open();
	    hb.getTransaction().begin();

	    try {
	        Departamento departamento = hb.getManager().find(Departamento.class, id);
	        hb.getTransaction().commit();
	        return Optional.ofNullable(departamento);
	    } catch (Exception e) {
	        if (hb.getTransaction().isActive()) {
	            hb.getTransaction().rollback();
	        }
	        throw e;
	    } finally {
	        hb.close();
	    }
	}

	@Override
	public boolean save(Departamento entity) {
		
	//TODO controlar que el jefe exista
		
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.getTransaction().begin();

        try {
            hb.getManager().merge(entity);
            hb.getTransaction().commit();
            hb.close();
            return true;

        } catch (Exception e) {
            throw new DepartamentoException("Error al guardar el departamento");
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
	}

	@Override
	public Boolean delete(Departamento entity) {
		 HibernateManager hb = HibernateManager.getInstance();
		    hb.open();
		    hb.getTransaction().begin();

		    try {
		        entity = hb.getManager().merge(entity); // Merge si no está en contexto persistente
		        hb.getManager().remove(entity);
		        hb.getTransaction().commit();
		        return true;
		    } catch (Exception e) {
		        if (hb.getTransaction().isActive()) {
		            hb.getTransaction().rollback();
		        }
		        throw e;
		    } finally {
		        hb.close();
		    }

}
}
