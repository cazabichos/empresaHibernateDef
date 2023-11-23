package repositories.departamentos;

import java.util.List;
import java.util.Optional;



import db.HibernateManager;
import exceptions.DepartamentoException;
import jakarta.persistence.TypedQuery;
import models.Departamento;

public class DepartamentosRepositoryImpl implements DepartamentosRepository {
	
	/**
     * Obtiene la lista de todos los departamentos.
     * @return Lista de departamentos.
     */
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

	/**
     * Busca un departamento por su ID.
     * @param id ID del departamento a buscar.
     * @return Optional que contiene el departamento si se encuentra, o vacío si no.
     */
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

	/**
     * Guarda o actualiza un departamento en la base de datos.
     * @param entity Departamento a guardar o actualizar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
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

	/**
     * Elimina un departamento de la base de datos.
     * @param entity Departamento a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
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
