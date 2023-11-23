package repositories.proyectos;

import java.util.List;
import java.util.Optional;

import db.HibernateManager;
import jakarta.persistence.TypedQuery;
import models.Proyecto;

public class ProyectosRepositoryImpl implements ProyectosRepository{

	/**
     * Obtiene la lista de todos los proyectos.
     * @return Lista de proyectos.
     */
	@Override
	public List<Proyecto> findAll() {
		HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.getTransaction().begin();

        try {
            TypedQuery<Proyecto> query = hb.getManager().createQuery("SELECT p FROM Proyecto p", Proyecto.class);
            List<Proyecto> proyectos = query.getResultList();

            hb.getTransaction().commit();
            return proyectos;
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
     * Busca un proyecto por su ID.
     * @param id ID del proyecto a buscar.
     * @return Optional que contiene el proyecto si se encuentra, o vacío si no.
     */
	@Override
	public Optional<Proyecto> findById(Integer id) {
		HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.getTransaction().begin();

        try {
            Proyecto proyecto = hb.getManager().find(Proyecto.class, id);
            hb.getTransaction().commit();
            return Optional.ofNullable(proyecto);
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
     * Guarda o actualiza un proyecto en la base de datos.
     * @param entity Proyecto a guardar o actualizar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
	@Override
	public boolean save(Proyecto entity) {
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
     * Elimina un proyecto de la base de datos.
     * @param entity Proyecto a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
	@Override
	public Boolean delete(Proyecto entity) {
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
