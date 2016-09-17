package cz.eclub.iot.model;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum DbUtils {
    INSTANCE;

    private static EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("cz.eclub.iot.server.db.hibernate");


    public EntityManager getEntityManager() {
        return sessionFactory.createEntityManager();
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }

    public static DbUtils getInstance() {
        return INSTANCE;
    }


}
