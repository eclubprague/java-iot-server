package cz.eclub.iot.model.DAO;

import cz.eclub.iot.model.classes.AbstractEntity;
import java.util.Collection;

public interface IAbstractDao<T extends AbstractEntity> {
    void addNew(T entity);

    Collection<T> getAll();

    boolean exists(T entity);

    T getById(int id);

    void update(T entity);

    void delete(T entity);


}
