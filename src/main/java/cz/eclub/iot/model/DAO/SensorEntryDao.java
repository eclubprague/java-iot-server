package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.SensorEntryEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class SensorEntryDao extends AbstractDao<SensorEntryEntity> {

    public Collection<SensorEntryEntity> getByUUIDLimit(String uuid, String unit, int limit) {
        EntityManager entityManager = DbUtils.getInstance().getSessionFactory().createEntityManager();
        Query q = entityManager.createQuery("select s from SensorEntryEntity as s where s.unit=:unit and s.sensor._UUID=:uuid order by s.timestamp desc");
        q.setParameter("unit", unit);
        q.setParameter("uuid", uuid);
        if (limit > 0)
            q.setMaxResults(limit);
        List result = q.getResultList();
        entityManager.close();
        return result;
    }

    public Collection<SensorEntryEntity> getByUUIDLimit(String uuid, int limit) {
        EntityManager entityManager = DbUtils.getInstance().getSessionFactory().createEntityManager();
        Query q = entityManager.createQuery("select s from SensorEntryEntity as s where s.sensor._UUID=:uuid order by s.timestamp desc");
        q.setParameter("uuid", uuid);
        if (limit > 0)
            q.setMaxResults(limit);
        List result = q.getResultList();
        entityManager.close();
        return result;
    }
}
