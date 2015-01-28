package br.com.wcj.grupoestudos.envers.persistence;

import java.util.Optional;

public interface Repository<T, PK> {
    Optional<T> find(PK id);

    void remove(T entity);

    void persist(T entity);

    Optional<T> update(T entity);
}
