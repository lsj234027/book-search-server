package com.example.booksearchserver.service.book;

import com.example.booksearchserver.domain.book.BookSearchHistory;
import com.example.booksearchserver.domain.book.BookSearchHistoryRepository;
import com.example.booksearchserver.service.base.BaseService;
import com.example.booksearchserver.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchHistoryService extends BaseService {

  @Autowired
  BookSearchHistoryRepository repository;

  public void addHistory() {
    final BookSearchHistory history = new BookSearchHistory();
    repository.add(new BookSearchHistory());

  }
}
