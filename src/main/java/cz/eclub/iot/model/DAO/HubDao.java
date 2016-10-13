package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.DbUtils;
import cz.eclub.iot.model.classes.HubEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class HubDao extends AbstractDao<HubEntity> {
    public Collection<HubEntity> getAllHubs() {
        Query q = getEntityManager().createQuery("select h from HubEntity as h");
        List result = q.getResultList();
        closeEntityManager();
        return result;
    }
}
