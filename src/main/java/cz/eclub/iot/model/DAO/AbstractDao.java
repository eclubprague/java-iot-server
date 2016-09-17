package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

public class AbstractDao<T extends AbstractEntity> implements IAbstractDao<T>, Serializable {
    protected Class<T> persistentClass;

    public AbstractDao() {
        EntityManager entityManager = DbUtils.getInstance().getEntityManager();
        entityManager.getEntityManagerFactory().getCache().evictAll();
        persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public void addNew(T entity) {
        EntityManager entityManager = DbUtils.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        //entityManager.close();
    }

    @Override
    public Collection<T> getAll() {
        EntityManager entityManager = DbUtils.getInstance().getEntityManager();
        CriteriaQuery q = entityManager.getCriteriaBuilder().createQuery(persistentClass);
        Root<T> abstractRoot = q.from(persistentClass);
        q.select(abstractRoot);
        Collection<T> collection = entityManager.createQuery(q).getResultList();
        entityManager.close();
        return collection;
    }

    @Override
    public boolean exists(T entity) {
        EntityManager entityManager = DbUtils.getInstance().getEntityManager();
        CriteriaQuery q = entityManager.getCriteriaBuilder().createQuery(persistentClass);
        Root<T> abstractRoot = q.from(persistentClass);
        q.select(abstractRoot);
        ParameterExpression<Integer> p = entityManager.getCriteriaBuilder().parameter(Integer.class);

        q.where(entityManager.getCriteriaBuilder().equal(abstractRoot.get("id"), p));
        Query qq = entityManager.createQuery(q);
        entityManager.close();
        return !qq.setParameter(p, entity.getId()).getResultList().isEmpty();
    }

    @Override
    public T getById(int id) {
        EntityManager entityManager = DbUtils.getInstance().getEntityManager();
        CriteriaQuery q = entityManager.getCriteriaBuilder().createQuery(persistentClass);
        Root<T> abstractRoot = q.from(persistentClass);
        q.select(abstractRoot);
        ParameterExpression<Integer> p = entityManager.getCriteriaBuilder().parameter(Integer.class);

        q.where(entityManager.getCriteriaBuilder().equal(abstractRoot.get("id"), p));
        Query qq = entityManager.createQuery(q);
        List<T> resultList = qq.setParameter(p, id).getResultList();
        entityManager.close();
        return (resultList.isEmpty()) ? null : resultList.get(0);
    }

    @Override
    public void update(T entity) {
        EntityManager entityManager = DbUtils.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void delete(T entity) {
        EntityManager entityManager = DbUtils.getInstance().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
