package com.example.booksearchserver.service.auth;

import com.example.booksearchserver.domain.user.User;
import com.example.booksearchserver.service.base.BaseService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import springfox.documentation.annotations.Cacheable;

/**
 * AuthenticationService
 * 인증을 위한 서비스
 */
@Service
public class AuthenticationService extends BaseService {

  private static final String SECRET_KEY = "key"; // 임시 설정

  public boolean authenticate(String token) {
    return isValid(token);
  }

  /**
   * user정보로 jwt 생성
   * @param user
   * @return
   */
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

  /**
   * token 체크
   * @param token
   * @return
   */
  @Cacheable(value="validTokenCache")
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

  /**
   * token 에서 userid 추출
   * @param token
   * @return
   */
  @Cacheable(value="getUserByTokenCache")
  public String getUserIdByToken(String token) throws Exception {
    Jws<Claims> claims = Jwts.parser()
            .setSigningKey(this.generateKey())
            .parseClaimsJws(token);
    return claims.getBody().get("userid", String.class);
  }

  /**
   * secretkey byte로 변환
   * @return
   */
  @Cacheable(value="genKeyCache")
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
