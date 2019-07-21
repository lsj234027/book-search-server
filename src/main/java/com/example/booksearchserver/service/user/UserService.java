package com.example.booksearchserver.service.user;

import com.example.booksearchserver.controller.user.UserView;
import com.example.booksearchserver.domain.user.User;
import com.example.booksearchserver.domain.user.UserRepository;
import com.example.booksearchserver.service.auth.AuthenticationService;
import com.example.booksearchserver.service.base.BaseService;
import com.example.booksearchserver.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationService authService;

  public void createUser(UserView userView) {
    final User user = new User();
    user.setUserid(userView.getUserid());
    user.setUsername(userView.getUsername());

    final String salt = SHA256Util.generateSalt();
    user.setSalt(salt);

    final String password = SHA256Util.getEncrypt(userView.getPassword(), salt);
    user.setPassword(password);

    userRepository.add(user);
  }

  public String signin(UserView userView) {
    final User user = userRepository.findUserByUserId(userView.getUserid());
    final String token = authService.generateToken(user);
    user.setToken(token);
    userRepository.update(user);
    return token;
  }
}
