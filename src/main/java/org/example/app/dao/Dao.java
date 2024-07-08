package org.example.app.dao;

import java.util.List;

public interface Dao<T> {
    void save(T entity);
    T findById(Long ID);
    void update(T entity);
    void delete(Long id);
    List<T> findAll();
}
