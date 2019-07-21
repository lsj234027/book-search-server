package com.example.booksearchserver.domain.user;

import com.example.booksearchserver.domain.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Table(uniqueConstraints = @UniqueConstraint(columnNames={"USERID"}))
@Entity
public class User extends BaseEntity {

  private String userid;
  private String username;
  private String password;
  private String token;
  private String salt;

  public User() {
  }

  public User(String userid, String username, String password) {
    this.userid = userid;
    this.username = username;
    this.password = password;
  }

  public User(String userid, String username, String password, String token) {
    this.userid = userid;
    this.username = username;
    this.password = password;
    this.token = token;
  }

  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }
}
