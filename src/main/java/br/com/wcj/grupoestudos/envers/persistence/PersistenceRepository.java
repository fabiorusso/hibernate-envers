package br.com.wcj.grupoestudos.envers.persistence;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class PersistenceRepository<T, PK> implements Repository<T, PK> {

    @PersistenceContext
    private EntityManager em;
    private Class<T> clazz;

    protected PersistenceRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    public PersistenceRepository() {
    }

    protected EntityManager getEm() {
        return this.em;
    }

    @Override
    public Optional<T> find(PK id) {
        return Optional.ofNullable(em.find(clazz, id));
    }

    @Override
    public void remove(T entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }

    @Override
    public void persist(T entity) {
        em.persist(entity);
    }

    @Override
    public Optional<T> update(T entity) {
        return Optional.ofNullable(em.merge(entity));
    }

}
