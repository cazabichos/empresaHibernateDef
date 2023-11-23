package db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.Getter;

/**
 * Controlador de Entidades de Hibernate JPA
 */
@Getter
public class HibernateManager {
    private static HibernateManager controller;

    // Creamos las EntityManagerFactory para manejar las entidades y transacciones
    private EntityManagerFactory entityManagerFactory;
    private EntityManager manager;
    private EntityTransaction transaction;

    // Constructor privado para implementar el patrón Singleton
    private HibernateManager() {
    }

    /**
     * Método estático que devuelve la única instancia de HibernateManager.
     * @return Instancia de HibernateManager.
     */
    public static HibernateManager getInstance() {
        if (controller == null)
            controller = new HibernateManager();
        return controller;
    }

    /**
     * Método para abrir la conexión con la base de datos.
     */
    public void open() {
        entityManagerFactory = Persistence.createEntityManagerFactory("unidad-persistencia");
        manager = entityManagerFactory.createEntityManager();
        transaction = manager.getTransaction();
    }

    /**
     * Método para cerrar la conexión con la base de datos.
     */
    public void close() {
        manager.close();
        entityManagerFactory.close();
    }
}