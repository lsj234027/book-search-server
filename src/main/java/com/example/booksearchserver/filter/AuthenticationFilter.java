package com.example.booksearchserver.filter;

import com.example.booksearchserver.service.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFilter implements Filter {
  private static final String[] skipURIs = {"/api/v1/user/signin", "/api/v1/user/signup", "/console", "/console/"};

  @Autowired
  private AuthenticationService authService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    final HttpServletRequest req = (HttpServletRequest)request;
    final HttpServletResponse res = (HttpServletResponse)response;


    boolean skip = false;
    for (String skipURI : skipURIs) {
      if (req.getRequestURI().contains(skipURI)) {
        skip = true;
        break;
      }
    }
    if (skip || "OPTIONS".equals(req.getMethod())) {
      chain.doFilter(req, res);
      return;
    }

    final String token = req.getHeader("x-auth-token");
    if (token != null && !token.isEmpty()) {
      if (!authService.authenticate(token)) {
        throw new ServletException();
      }
    } else {
      throw new ServletException();
    }
    chain.doFilter(req, res);
  }
}
