package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.classes.SensorEntryEntity;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class SensorEntryDao extends AbstractDao<SensorEntryEntity> {

    public Collection<SensorEntryEntity> getByUUIDLimit(String unit, int limit) {
        Query q = getEntityManager().createQuery("select s from SensorEntryEntity as s where s.unit=:unit");
        q.setParameter("unit", unit);
        if (limit > 0)
            q.setMaxResults(limit);
        List result = q.getResultList();
        closeEntityManager();
        return result;
    }
}
