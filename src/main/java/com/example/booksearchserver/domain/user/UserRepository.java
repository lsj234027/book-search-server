package com.example.booksearchserver.domain.user;

import com.example.booksearchserver.domain.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserRepository extends BaseRepository<User> {

  public User findUserByUserId(String userid) {
    CriteriaQuery<User> query = getBuilder().createQuery(User.class);
    Root<User> root = query.from(User.class);
    Predicate predicate = getBuilder().equal(root.get("userid"), userid);
    query.select(root).where(predicate);
    TypedQuery<User> q = entityManager.createQuery(query);
    return q.getSingleResult();
  }
}
