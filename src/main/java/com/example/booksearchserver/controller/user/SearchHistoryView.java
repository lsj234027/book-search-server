package com.example.booksearchserver.controller.user;

import java.io.Serializable;

public class SearchHistoryView implements Serializable {
  private String keyword;
  private long count;

  public SearchHistoryView(String keyword, long count) {
    this.keyword = keyword;
    this.count = count;
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
}
