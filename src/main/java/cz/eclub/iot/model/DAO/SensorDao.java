package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.SensorEntity;

import java.util.Collection;
import java.util.List;


public class SensorDao extends AbstractDao<SensorEntity> {

    public SensorEntity getByUUID(String UUID) {
        SensorEntity returnValue = null;
        try {

            DbUtils.getInstance().getTransactionManager().begin();
            List<SensorEntity> results = getEntityManager().createNativeQuery("{\"_UUID\":\""+UUID+"\"}", SensorEntity.class).getResultList();
            DbUtils.getInstance().getTransactionManager().commit();

            if (results.size() == 1)
                returnValue = results.get(0);

        } catch (Exception e) {
            e.printStackTrace();
            //rollback(e);
        } finally {
            closeEntityManager();
        }
        return returnValue;
    }

    public Collection<SensorEntity> getByUUIDLimit(String uuid, Integer limitResults) {
        return null;
    }


}
