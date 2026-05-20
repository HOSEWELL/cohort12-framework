package app.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.lang.reflect.ParameterizedType;
import java.util.*;

public abstract class GenericDao<T, ID> {

    @PersistenceContext
    private EntityManager em;

    public T save(T entity) {
        return em.merge(entity);
    }

    public T findById(ID id) {
        return em.find(getType(), id);
    }

    public List<T> findAll() {
        return em.createQuery("SELECT e FROM "
            + getType().getSimpleName() + " e", getType()).getResultList();
    }

    public void delete(ID id) {
        em.remove(em.find(getType(), id));
    }

    @SuppressWarnings("unchecked")
    public Class<T> getType() {
        ParameterizedType superClass =
                (ParameterizedType) getClass().getGenericSuperclass();

        return (Class<T>) superClass.getActualTypeArguments()[0];
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

}