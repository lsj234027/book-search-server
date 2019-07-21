package com.example.booksearchserver.controller.user;

import com.example.booksearchserver.domain.user.User;
import com.example.booksearchserver.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/signup")
  public boolean signup(@RequestBody UserView userView) {
    userService.createUser(userView);
    return true;
  }

  @PostMapping("/signin")
  public String signin(HttpServletResponse response, @RequestBody UserView userView) {
    final String token = userService.signin(userView);
    response.setHeader("x-auth-token", token);
    return token;
  }
}
