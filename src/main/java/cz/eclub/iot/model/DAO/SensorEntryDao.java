package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.SensorEntity;
import cz.eclub.iot.model.classes.SensorEntryEntity;

import javax.persistence.Query;
import java.util.Collection;

public class SensorEntryDao extends AbstractDao<SensorEntryEntity> {
    public Collection<SensorEntryEntity> getByIdUnitLimit(SensorEntity sensor, String unit, Integer limitResults) {
        Collection<SensorEntryEntity> returnValue = null;
        try {
            DbUtils.getInstance().getTransactionManager().begin();
            Query q = getEntityManager().createQuery("from SensorEntryEntity se where se.unit=:unit and se.sensor=:sensor order by se.timestamp desc");
            q.setParameter("unit",unit);
            q.setParameter("sensor",sensor);
            q.setMaxResults(limitResults);
            returnValue = q.getResultList();
            DbUtils.getInstance().getTransactionManager().commit();
        } catch (Exception e) {
            e.printStackTrace();
            rollback(e);
        } finally {
            closeEntityManager();
        }
        return returnValue;
    }

    public Collection<SensorEntryEntity> getByIdLimit(SensorEntity sensor, Integer limitResults) {
        Collection<SensorEntryEntity> returnValue = null;
        try {
            DbUtils.getInstance().getTransactionManager().begin();
            javax.persistence.Query q = getEntityManager().createQuery("from SensorEntryEntity se where se.sensor=:sensor order by se.timestamp desc");
            q.setParameter("sensor",sensor);
            q.setMaxResults(limitResults);
            returnValue = q.getResultList();
            DbUtils.getInstance().getTransactionManager().commit();

        } catch (Exception e) {
            e.printStackTrace();
            rollback(e);
        } finally {
            closeEntityManager();
        }
        return returnValue;
    }
}
