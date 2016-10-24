package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.SensorEntity;
import cz.eclub.iot.model.classes.SensorEntryEntity;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SensorEntryDao extends AbstractDao<SensorEntryEntity> {
    public Collection<SensorEntryEntity> getByUUIDLimit(String uuid, String unit, Integer limitResults) {
        Collection<SensorEntryEntity> returnValue = new ArrayList<>();
        try {
            DbUtils.getInstance().getTransactionManager().begin();
            returnValue = getEntityManager().createNativeQuery("{\"sensor._UUID\":\""+uuid+"\"}", SensorEntryEntity.class).getResultList();
            DbUtils.getInstance().getTransactionManager().commit();

        } catch (Exception e) {
            e.printStackTrace();
            rollback(e);
        } finally {
            closeEntityManager();
        }
        return returnValue;
    }

    public Collection<SensorEntryEntity> getByUUIDLimit(String uuid, Integer limitResults) {
        return null;
    }
}
