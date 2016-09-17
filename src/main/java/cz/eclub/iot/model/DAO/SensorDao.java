package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.SensorEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class SensorDao extends AbstractDao<SensorEntity> {


    public SensorEntity getByUUID(String uuid) {
        EntityManager entityManager = DbUtils.getInstance().getSessionFactory().createEntityManager();
        Query q = entityManager.createQuery("select s from SensorEntity as s where s._UUID=:uuid");
        q.setParameter("uuid", uuid);
        List result = q.getResultList();
        if (result.size() == 0)
            return null; // <-- to se mi nelibi

        entityManager.close();
        return (SensorEntity) result.get(0);
    }

    public Collection<SensorEntity> getByUUIDLimit(String uuid, int limit) {
        EntityManager entityManager = DbUtils.getInstance().getSessionFactory().createEntityManager();
        Query q = entityManager.createQuery("select s from SensorEntity as s where s._UUID=:uuid");
        q.setParameter("uuid", uuid);
        if (limit > 0) // <-- to se mi nelibi
            q.setMaxResults(limit);
        List result = q.getResultList();
        entityManager.close();
        return result;
    }

}
