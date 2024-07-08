package org.example.app.service;

import org.example.app.dao.Dao;

import java.util.List;

public class SpaceTravelService<T> {

    private final Dao<T> dao;

    public SpaceTravelService(Dao<T> dao) {
        this.dao = dao;
    }

    public void create(T entity) {
        dao.save(entity);
    }

    public T findById(Long id) {
        return dao.findById(id);
    }

    public void update(T entity) {
        dao.update(entity);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

    public List<T> findAll() {
        return dao.findAll();
    }
}
