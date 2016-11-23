package br.com.aelkz.repository;

import br.com.aelkz.exception.EntityException;

import java.util.Set;

public interface GenericRepository<T> {

    public T find(T entity) throws EntityException;

    public T findBy(Integer id) throws EntityException;

    public void save(T entity) throws EntityException;

    public void update(T entity) throws EntityException;

    public void remove(T entity) throws EntityException;

    public Set<T> getStorage() throws EntityException;

    public Integer getNextVal() throws EntityException;

}
