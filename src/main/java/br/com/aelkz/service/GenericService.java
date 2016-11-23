package br.com.aelkz.service;

import br.com.aelkz.exception.EntityException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

public interface GenericService<T> {

    public void save() throws EntityException;

    public void preUpdate(T entity) throws EntityException;

    public void update() throws EntityException;

    public void preRemove(T entity) throws EntityException;

    public void remove(T entity) throws EntityException;

    public Set<T> getAll() throws EntityException;

    public Boolean validate(T entity) throws EntityException;

    public default void reload() throws EntityException, IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    public void cancel() throws EntityException;

}
