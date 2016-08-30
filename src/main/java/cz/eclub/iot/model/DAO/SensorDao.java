package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.classes.HubEntity;
import cz.eclub.iot.model.classes.SensorEntity;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class SensorDao extends AbstractDao<SensorEntity> {
    public Collection<SensorEntity> getByUUID(String uuid) {
        Query q = getEntityManager().createQuery("select s from SensorEntity as s where s._UUID=:uuid order by s.timestamp DESC");
        q.setParameter("uuid",uuid);
        List result = q.getResultList();
        closeEntityManager();
        return result;
    }

    public Collection<SensorEntity> getByUUIDLimit(String uuid, int limit) {
        Query q = getEntityManager().createQuery("select s from SensorEntity as s where s._UUID=:uuid order by s.timestamp DESC ");
        q.setParameter("uuid",uuid);
        if(limit > 0)
            q.setMaxResults(limit);
        List result = q.getResultList();
        closeEntityManager();
        return result;
    }

    public Collection<String> getAllUUIDs() {
        Query q = getEntityManager().createQuery("select s._UUID from SensorEntity as s group by s._UUID");
        List result = q.getResultList();
        closeEntityManager();
        return result;
    }
}
