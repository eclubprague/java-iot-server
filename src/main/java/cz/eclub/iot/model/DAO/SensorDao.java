package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.classes.SensorEntity;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class SensorDao extends AbstractDao<SensorEntity> {
    public SensorEntity getByUUID(String uuid) {
        Query q = getEntityManager().createQuery("select s from SensorEntity as s where s._UUID=:uuid");
        q.setParameter("uuid", uuid);
        List result = q.getResultList();
        closeEntityManager();

        if (result.size() == 0)
            return null;

        return (SensorEntity) result.get(0);
    }

    public Collection<SensorEntity> getByUUIDLimit(String uuid, int limit) {
        Query q = getEntityManager().createQuery("select s from SensorEntity as s where s._UUID=:uuid");
        q.setParameter("uuid", uuid);
        if (limit > 0)
            q.setMaxResults(limit);
        List result = q.getResultList();
        closeEntityManager();
        return result;
    }

}
