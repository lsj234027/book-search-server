package com.example.booksearchserver.service.user;

import com.example.booksearchserver.controller.user.UserView;
import com.example.booksearchserver.domain.user.User;
import com.example.booksearchserver.domain.user.UserRepository;
import com.example.booksearchserver.infra.exception.BookException;
import com.example.booksearchserver.service.auth.AuthenticationService;
import com.example.booksearchserver.service.base.BaseService;
import com.example.booksearchserver.infra.util.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationService authService;

  public void createUser(UserView userView) throws Exception {
    final User user = new User();
    user.setUserid(userView.getUserid());
    user.setUsername(userView.getUsername());

    final String salt = SHA256Util.generateSalt();
    user.setSalt(salt);

    final String password = SHA256Util.getEncrypt(userView.getPassword(), salt);
    user.setPassword(password);

    if (userRepository.findUserByUserId(userView.getUserid()) != null){
      throw new BookException("이미 존재하는 아이디입니다.");
    }
    userRepository.add(user);
  }

  public String signin(UserView userView) throws Exception {
    final User user = userRepository.findUserByUserId(userView.getUserid());
    final String inputPassword = SHA256Util.getEncrypt(userView.getPassword(), user.getSalt());
    if (!inputPassword.equals(user.getPassword())) {
      throw new BookException("비밀번호를 잘못 입력하였습니다.");
    }
    final String token = authService.generateToken(user);
    user.setToken(token);
    userRepository.update(user);
    return token;
  }
}
