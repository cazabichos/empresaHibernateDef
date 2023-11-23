package repositories.empleados;

import java.util.List;
import java.util.Optional;

import db.HibernateManager;
import jakarta.persistence.TypedQuery;
import models.Empleado;

public class EmpleadosRepositoryImpl implements EmpleadosRepository {

	/**
     * Obtiene la lista de todos los empleados.
     * @return Lista de empleados.
     */
	@Override
	public List<Empleado> findAll() {
		HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.getTransaction().begin();

        try {
            TypedQuery<Empleado> query = hb.getManager().createQuery("SELECT e FROM Empleado e", Empleado.class);
            List<Empleado> empleados = query.getResultList();

            hb.getTransaction().commit();
            return empleados;
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
     * Busca un empleado por su ID.
     * @param id ID del empleado a buscar.
     * @return Optional que contiene el empleado si se encuentra, o vacío si no.
     */
	@Override
	public Optional<Empleado> findById(Integer id) {
		HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.getTransaction().begin();

        try {
            Empleado empleado = hb.getManager().find(Empleado.class, id);
            hb.getTransaction().commit();
            return Optional.ofNullable(empleado);
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
     * Guarda o actualiza un empleado en la base de datos.
     * @param entity Empleado a guardar o actualizar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
	@Override
	public boolean save(Empleado entity) {
		  HibernateManager hb = HibernateManager.getInstance();
	        hb.open();
	        hb.getTransaction().begin();

	        try {
	            hb.getManager().merge(entity);
	            hb.getTransaction().commit();
	            hb.close();
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

	/**
     * Elimina un empleado de la base de datos.
     * @param entity Empleado a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
	@Override
	public Boolean delete(Empleado entity) {
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

} }
