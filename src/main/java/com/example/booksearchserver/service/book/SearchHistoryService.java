package com.example.booksearchserver.service.book;

import com.example.booksearchserver.controller.user.SearchHistoryView;
import com.example.booksearchserver.domain.book.BookSearchHistory;
import com.example.booksearchserver.domain.book.BookSearchHistoryRepository;
import com.example.booksearchserver.domain.user.User;
import com.example.booksearchserver.domain.user.UserRepository;
import com.example.booksearchserver.service.auth.AuthenticationService;
import com.example.booksearchserver.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchHistoryService extends BaseService {

  @Autowired
  AuthenticationService authService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  BookSearchHistoryRepository historyRepository;


  public void addHistory(String userid, String keyword) {
    final User user = userRepository.findUserByUserId(userid);

    BookSearchHistory history = historyRepository.findHistoryByUserKeyword(user, keyword);
    if (history == null) {
      history = new BookSearchHistory();
      history.setCount(1);
      history.setUser(user);
      history.setKeyword(keyword);
      historyRepository.add(history);
      return;
    }
    history.setCount(history.getCount() + 1);
    historyRepository.update(history);
  }

  public List<SearchHistoryView> getMyHistories(String userid) {
    final User user = userRepository.findUserByUserId(userid);
    return historyRepository.findHistoryByUser(user).stream().map(origin -> {
      return new SearchHistoryView(origin.getKeyword(), origin.getCount(), origin.getUpdateDate());
    }).collect(Collectors.toList());
  }

  public List<SearchHistoryView> getTop10Histories() {
    return historyRepository.getTop10History();
  }
}
