package com.example.booksearchserver.infra.filter;

import com.example.booksearchserver.infra.config.Constants;
import com.example.booksearchserver.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationFilter
 * validate token in request header
 */
@Component
public class AuthenticationFilter implements Filter {
  private static final String[] skipURIs = {"/api/v1/user/signin", "/api/v1/user/signup", "/console", "/console/"};

  @Autowired
  private AuthenticationService authService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    final HttpServletRequest req = (HttpServletRequest)request;
    final HttpServletResponse res = (HttpServletResponse)response;

    // api 가 아닌 경우 인증절차 X
    if(!req.getRequestURI().contains("/api/v1")) {
      chain.doFilter(req, res);
      return;
    }

    // api 중 signin, signup 에 관한 경우 인증절차 X
    boolean skip = false;
    for (String skipURI : skipURIs) {
      if (req.getRequestURI().contains(skipURI)) {
        skip = true;
        break;
      }
    }
    // preflight 통과
    if (skip || "OPTIONS".equals(req.getMethod())) {
      chain.doFilter(req, res);
      return;
    }

    final String token = req.getHeader(Constants.TOKEN_HEADER);
    if (token != null && !token.isEmpty()) {
      if (!authService.authenticate(token)) {
        throw new ServletException("authentication failed");
      }
    } else {
      throw new ServletException("token is empty");
    }
    chain.doFilter(req, res);
  }
}
