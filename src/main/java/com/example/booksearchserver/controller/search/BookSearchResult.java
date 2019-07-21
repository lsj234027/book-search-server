package com.example.booksearchserver.controller.search;

import com.example.booksearchserver.rest.kakao.Document;

import java.io.Serializable;
import java.util.List;

public class BookSearchResult implements Serializable {
  private List<Document> books;
  private Page page;

  public List<Document> getBooks() {
    return books;
  }

  public BookSearchResult setBooks(List<Document> books) {
    this.books = books;
    return this;
  }

  public Page getPage() {
    return page;
  }

  public BookSearchResult setPage(Page page) {
    this.page = page;
    return this;
  }
}
