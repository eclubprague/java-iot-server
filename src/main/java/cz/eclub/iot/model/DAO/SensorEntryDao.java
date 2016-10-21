package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.SensorEntryEntity;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.Collection;

public class SensorEntryDao extends AbstractDao<SensorEntryEntity> {
    public Collection<SensorEntryEntity> getByUUIDLimit(String uuid, String unit, Integer limitResults) {
        Collection<SensorEntryEntity> returnValue = null;
        try {
            FullTextEntityManager ftem = Search.getFullTextEntityManager(getEntityManager());

            DbUtils.getInstance().getTransactionManager().begin();

            QueryBuilder b = ftem.getSearchFactory()
                    .buildQueryBuilder()
                    .forEntity(SensorEntryEntity.class)
                    .get();

            Query lq = b.keyword().onField("_UUID").matching(uuid).createQuery();

            FullTextQuery ftQuery = ftem.createFullTextQuery(lq, SensorEntryEntity.class);

            returnValue = ftQuery.getResultList();

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
