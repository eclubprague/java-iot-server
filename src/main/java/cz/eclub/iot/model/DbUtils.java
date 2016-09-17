package cz.eclub.iot.model;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum DbUtils {
    INSTANCE;

    private static EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("cz.eclub.iot.server.db.hibernate");


    public EntityManagerFactory getSessionFactory() {
        return sessionFactory;
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }

    public static DbUtils getInstance() {
        return INSTANCE;
    }


}
