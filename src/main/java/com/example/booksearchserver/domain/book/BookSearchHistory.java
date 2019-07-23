package com.example.booksearchserver.domain.book;

import com.example.booksearchserver.domain.base.BaseEntity;
import com.example.booksearchserver.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 책 검색이력 Entity
 */
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"USER_ID", "KEYWORD"}))
@Entity
public class BookSearchHistory extends BaseEntity {
  private String keyword;
  private long count;

  @ManyToOne
  private User user;

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
