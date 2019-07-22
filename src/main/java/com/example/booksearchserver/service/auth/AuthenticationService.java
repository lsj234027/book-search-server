package com.example.booksearchserver.service.auth;

import com.example.booksearchserver.domain.user.User;
import com.example.booksearchserver.service.base.BaseService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class AuthenticationService extends BaseService {

  private static final String SECRET_KEY = "key";

  public boolean authenticate(String token) {
    return isValid(token);
  }

  public String generateToken(User user) {
    String token = Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("regDate", System.currentTimeMillis())
            .setSubject("book")
            .claim("userid", user.getUserid())
            .signWith(SignatureAlgorithm.HS256, this.generateKey())
            .compact();
    return token;
  }

  public boolean isValid(String token) {
    try{
      Jws<Claims> claims = Jwts.parser()
              .setSigningKey(this.generateKey())
              .parseClaimsJws(token);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public String getUserIdByToken(String token) {
    Jws<Claims> claims = Jwts.parser()
            .setSigningKey(this.generateKey())
            .parseClaimsJws(token);
    return claims.getBody().get("userid", String.class);
  }

  private byte[] generateKey() {
    byte[] secretKey = null;
    try {
      secretKey = SECRET_KEY.getBytes("utf-8");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return secretKey;
  }

}
