package cz.eclub.iot.model.DAO;


import cz.eclub.iot.model.classes.IEntity;

import java.util.Collection;

public interface IAbstractDao<T extends IEntity> {
    boolean addNew(T entity);

    Collection<T> getAll();

    boolean exists(T entity);

    T getById(int id);

    boolean update(T entity);

    boolean delete(T entity);
}