package com.example.booksearchserver.domain.book;

import com.example.booksearchserver.controller.user.SearchHistoryView;
import com.example.booksearchserver.domain.base.BaseRepository;
import com.example.booksearchserver.domain.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BookSearchHistoryRepository extends BaseRepository<Object> {

  private final int MAX_RESULT_COUNT = 10;

  /**
   * Top 10 검색어 반환
   * @return
   */
  public List<SearchHistoryView> getTop10History() {
    CriteriaQuery<Object[]> query = getBuilder().createQuery(Object[].class);
    Root<BookSearchHistory> root = query.from(BookSearchHistory.class);
    query.groupBy(root.get("keyword"));
    query.multiselect(root.get("keyword"),
      getBuilder().sum(root.get("count"))
    );
    query.orderBy(getBuilder().desc(getBuilder().sum(root.get("count"))));
    TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
    List<Object[]> resultList = typedQuery.setMaxResults(MAX_RESULT_COUNT).getResultList();
    List<SearchHistoryView> histories = new ArrayList<SearchHistoryView>();
    resultList.forEach(objects ->
            histories.add(new SearchHistoryView((String)objects[0], ((Long)objects[1]))));
    return histories;
  }

  /**
   * User, Keyword 로 검색이력 반환
   * @param user
   * @param keyword
   * @return
   */
  public BookSearchHistory findHistoryByUserKeyword(User user, String keyword) {
    CriteriaQuery<BookSearchHistory> query = getBuilder().createQuery(BookSearchHistory.class);
    Root<BookSearchHistory> root = query.from(BookSearchHistory.class);
    Predicate pre1 = getBuilder().equal(root.get("user"), user);
    Predicate pre2 = getBuilder().equal(root.get("keyword"), keyword);
    query.select(root).where(pre1, pre2);
    TypedQuery<BookSearchHistory> q = entityManager.createQuery(query);
    List<BookSearchHistory> list = q.getResultList();
    if (list.size() == 0) return null;
    return list.get(0);
  }

  /**
   * User 로 검색이력 반환
   * @param user
   * @return
   */
  public List<BookSearchHistory> findHistoryByUser(User user) {
    CriteriaQuery<BookSearchHistory> query = getBuilder().createQuery(BookSearchHistory.class);
    Root<BookSearchHistory> root = query.from(BookSearchHistory.class);
    Predicate pre = getBuilder().equal(root.get("user"), user);
    query.select(root).where(pre).orderBy(getBuilder().desc(root.get("updateDate")));
    TypedQuery<BookSearchHistory> q = entityManager.createQuery(query);
    return q.getResultList();
  }

}
