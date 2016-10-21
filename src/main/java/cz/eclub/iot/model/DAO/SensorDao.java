package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.SensorEntity;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.Collection;
import java.util.List;


public class SensorDao extends AbstractDao<SensorEntity> {

    public SensorEntity getByUUID(String UUID){
        SensorEntity returnValue = null;
        try {
            FullTextEntityManager ftem = Search.getFullTextEntityManager(getEntityManager());

            DbUtils.getInstance().getTransactionManager().begin();

            QueryBuilder b = ftem.getSearchFactory()
                    .buildQueryBuilder()
                    .forEntity(SensorEntity.class)
                    .get();

            Query lq = b.keyword().onField("_UUID").matching(UUID).createQuery();

            FullTextQuery ftQuery = ftem.createFullTextQuery(lq, SensorEntity.class);

            List<SensorEntity> resultList = ftQuery.getResultList();

            DbUtils.getInstance().getTransactionManager().commit();


            if(resultList.size()==1){
                returnValue = resultList.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            rollback(e);
        } finally {
            closeEntityManager();
        }
        return returnValue;
    }

    public Collection<SensorEntity> getByUUIDLimit(String uuid, Integer limitResults) {
        return null;
    }
}
