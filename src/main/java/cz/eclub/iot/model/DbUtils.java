package cz.eclub.iot.model;


import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

public enum DbUtils {
    INSTANCE;

    private static EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("ogm-jpa-tutorial");

    private static TransactionManager transactionManager = com.arjuna.ats.jta.TransactionManager.transactionManager();

    public static DbUtils getInstance() {
        return INSTANCE;
    }

    public EntityManager getEntityManager() {
        if (sessionFactory.isOpen())
            return sessionFactory.createEntityManager();
        else
            throw new RuntimeException("Session factory is closed!");
    }


    public TransactionManager getTransactionManager(){
        return transactionManager;
    }

    public FullTextEntityManager getFullTextEntityManager() {
        FullTextEntityManager ftem = Search.getFullTextEntityManager(getEntityManager());
        return ftem;
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }


}
