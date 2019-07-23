package com.example.booksearchserver.controller.user;

import com.example.booksearchserver.infra.config.Constants;
import com.example.booksearchserver.infra.exception.BookException;
import com.example.booksearchserver.service.auth.AuthenticationService;
import com.example.booksearchserver.service.book.SearchHistoryService;
import com.example.booksearchserver.service.user.UserService;
import io.jsonwebtoken.lang.Assert;
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

  /**
   * 회원가입
   * @param userView
   * @return
   * @throws Exception
   */
  @PostMapping("/signup")
  public boolean signup(@RequestBody UserView userView) throws Exception{
    if (userView.getUserid() == null || userView.getUserid().equals("")) {
      throw new BookException("아이디를 입력해주세요.");
    }
    userService.createUser(userView);
    return true;
  }

  /**
   * 로그인
   * @param response
   * @param userView
   * @return
   * @throws Exception
   */
  @PostMapping("/signin")
  public String signin(HttpServletResponse response, @RequestBody UserView userView) throws Exception {
    final String token = userService.signin(userView);
    response.setHeader(Constants.TOKEN_HEADER, token);
    return token;
  }

  /**
   * 내 검색이력 가져오기
   * @param request
   * @return
   * @throws Exception
   */
  @GetMapping("/histories")
  public List<SearchHistoryView> getMyHistories(HttpServletRequest request) throws Exception {
    final String token = request.getHeader(Constants.TOKEN_HEADER);
    final String userid = authService.getUserIdByToken(token);
    return searchHistoryService.getMyHistories(userid);
  }
}
