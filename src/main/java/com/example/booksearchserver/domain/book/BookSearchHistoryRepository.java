package com.example.booksearchserver.domain.book;

import com.example.booksearchserver.domain.base.BaseRepository;
import com.example.booksearchserver.domain.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Repository
public class BookSearchHistoryRepository extends BaseRepository<Object> {

  public Map<String, Integer> getTop10History() {
    CriteriaQuery<Object[]> query = getBuilder().createQuery(Object[].class);
    Root<BookSearchHistory> history = query.from(BookSearchHistory.class);
    query.groupBy(history.get("keyword"));
//    query.multiselect(history.get("keyword"),
//            getBuilder().count(history.get("count")));
    query.orderBy(getBuilder().desc(getBuilder().count(history.get("count"))));
    TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
    List<Object[]> resultList = typedQuery.getResultList();
    resultList.forEach(objects ->
            System.out.printf("Keyword: %s, Count: %s%n", objects[0], objects[1]));
    return null;
  }

//  public User findUserByUserId(String userid) {
//    CriteriaQuery<User> query = getBuilder().createQuery(User.class);
//    Root<User> root = query.from(User.class);
//    Predicate predicate = getBuilder().equal(root.get("userid"), userid);
//    query.select(root).where(predicate);
//    TypedQuery<User> q = entityManager.createQuery(query);
//    return q.getSingleResult();
//  }
}
