package cz.eclub.iot.model.DAO;


import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.IEntity;
import cz.eclub.iot.model.classes.SensorEntity;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

public class AbstractDao<T extends IEntity> implements IAbstractDao<T>, Serializable {
    protected Class<T> persistentClass;
    private EntityManager entityManager;

    public AbstractDao() {
        entityManager = DbUtils.getInstance().getEntityManager();
        entityManager.getEntityManagerFactory().getCache().evictAll();
        persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected EntityManager getEntityManager() {
        if (!entityManager.isOpen()) {
            entityManager = DbUtils.getInstance().getEntityManager();
        }
        return entityManager;
    }

    protected void closeEntityManager() {
        if (!entityManager.isOpen()) {
            entityManager.flush();
            entityManager.close();
        }
    }

    @Override
    public boolean addNew(T entity) { //TODO should throw exception
        boolean returnValue = false;
        try {
            DbUtils.getInstance().getTransactionManager().begin();
            getEntityManager().persist(entity);
            DbUtils.getInstance().getTransactionManager().commit();
            returnValue = true;
        } catch (Exception e) {
            System.out.println("daco sa pojebalo");
            //e.printStackTrace();
        } finally {
            closeEntityManager();
        }
        return returnValue;
    }


    @Override
    public Collection<T> getAll() {
        Collection<T> returnValue = null;
        try {
            FullTextEntityManager ftem = Search.getFullTextEntityManager(getEntityManager());

            DbUtils.getInstance().getTransactionManager().begin();

            QueryBuilder b = ftem.getSearchFactory()
                    .buildQueryBuilder()
                    .forEntity(persistentClass)
                    .get();

            Query lq = b.all().createQuery();

            FullTextQuery ftQuery = ftem.createFullTextQuery(lq, SensorEntity.class);

            returnValue = ftQuery.getResultList();

            DbUtils.getInstance().getTransactionManager().commit();

        } catch (Exception e) {
            e.printStackTrace();
            rollback(e);
        } finally {
            closeEntityManager();
        }
        return returnValue;
    }

    @Override
    public boolean exists(T entity) {
        return false;
    }

    @Override
    public boolean update(T entity) { //TODO should throw exception
        boolean returnValue = false;
        try {
            DbUtils.getInstance().getTransactionManager().begin();
            getEntityManager().merge(entity);
            DbUtils.getInstance().getTransactionManager().commit();
            returnValue = true;
        } catch (Exception e) {
            rollback(e);
        } finally {
            closeEntityManager();
        }
        return returnValue;
    }

    @Override
    public boolean delete(T entity) { //TODO should throw exception
        boolean returnValue = false;
        try {
            DbUtils.getInstance().getTransactionManager().begin();
            getEntityManager().remove(entity);
            DbUtils.getInstance().getTransactionManager().commit();
            returnValue = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeEntityManager();
        }
        return returnValue;
    }


    protected boolean rollback(Exception e) {
        e.printStackTrace();
//        try {
//            e.printStackTrace();
//            //DbUtils.getInstance().getTransactionManager().rollback();
//        } catch (SystemException e1) {
//            e1.printStackTrace();
//        }
        return false;
    }

}
