package com.example.booksearchserver.controller.user;


import lombok.NoArgsConstructor;

import java.io.Serializable;

public class UserView implements Serializable {
  private String userid;
  private String password;
  private String username;
  private String token;

  public UserView() {
  }

  public UserView(String userid, String password, String username) {
    this.userid = userid;
    this.password = password;
    this.username = username;
  }

  public UserView(String userid, String password, String username, String token) {
    this.userid = userid;
    this.password = password;
    this.username = username;
    this.token = token;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
