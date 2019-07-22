package com.example.booksearchserver.controller.user;

import com.example.booksearchserver.config.Constants;
import com.example.booksearchserver.domain.book.BookSearchHistory;
import com.example.booksearchserver.domain.user.User;
import com.example.booksearchserver.service.auth.AuthenticationService;
import com.example.booksearchserver.service.book.SearchHistoryService;
import com.example.booksearchserver.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationService authService;

  @Autowired
  private SearchHistoryService searchHistoryService;

  @PostMapping("/signup")
  public boolean signup(@RequestBody UserView userView) {
    userService.createUser(userView);
    return true;
  }

  @PostMapping("/signin")
  public String signin(HttpServletResponse response, @RequestBody UserView userView) throws Exception {
    final String token = userService.signin(userView);
    response.setHeader(Constants.TOKEN_HEADER, token);
    return token;
  }

  @GetMapping("/histories")
  public List<BookSearchHistory> getMyHistories(HttpServletRequest request) {
    final String token = request.getHeader(Constants.TOKEN_HEADER);
    final String userid = authService.getUserIdByToken(token);
    return searchHistoryService.getMyHistories(userid);
  }
}
