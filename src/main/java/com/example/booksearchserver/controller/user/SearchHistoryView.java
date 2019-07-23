package com.example.booksearchserver.controller.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 검색 이력 View
 */
public class SearchHistoryView implements Serializable {
  private String keyword;
  private long count;
  private Date updateDate;

  public SearchHistoryView(String keyword, long count) {
    this.keyword = keyword;
    this.count = count;
  }

  public SearchHistoryView(String keyword, long count, Date updateDate) {
    this.keyword = keyword;
    this.count = count;
    this.updateDate = updateDate;
  }

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

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
}
