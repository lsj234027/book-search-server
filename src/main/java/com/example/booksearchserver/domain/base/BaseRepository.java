package com.example.booksearchserver.domain.base;

import org.springframework.stereotype.Repository;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * Abstract class includes basic CRUD methods
 * @see EntityManager
 */
@Repository
public abstract class BaseRepository<T> {
  @PersistenceContext
  protected EntityManager entityManager;

  public CriteriaBuilder getBuilder() {
    return entityManager.getCriteriaBuilder();
  }

  public void add(BaseEntity entity) {
    entityManager.persist(entity);
  }

  public void update(BaseEntity entity) {
    entityManager.merge(entity);
  }

  public void remove(BaseEntity entity) {
    entityManager.remove(entity);
  }

}
