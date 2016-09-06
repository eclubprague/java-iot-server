package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.classes.SensorEntryEntity;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class SensorEntryDao extends AbstractDao<SensorEntryEntity> {

    public Collection<SensorEntryEntity> getByUUIDLimit(String uuid, String unit, int limit) {
        Query q = getEntityManager().createQuery("select s from SensorEntryEntity as s where s.unit=:unit and s.sensor._UUID=:uuid");
        q.setParameter("unit", unit);
        q.setParameter("uuid", uuid);
        if (limit > 0)
            q.setMaxResults(limit);
        List result = q.getResultList();
        closeEntityManager();
        return result;
    }

    public Collection<SensorEntryEntity> getByUUIDLimit(String uuid, int limit) {
        Query q = getEntityManager().createQuery("select s from SensorEntryEntity as s where s.sensor._UUID=:uuid");
        q.setParameter("uuid", uuid);
        if (limit > 0)
            q.setMaxResults(limit);
        List result = q.getResultList();
        closeEntityManager();
        return result;
    }
}
