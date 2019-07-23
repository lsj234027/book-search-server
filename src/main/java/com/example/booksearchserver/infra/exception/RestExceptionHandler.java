package com.example.booksearchserver.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  private static final String DEFAULT_ERROR_MESSAGE = "작업 중 에러가 발생했습니다. 관리자에게 문의하세요.";

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleBookSearchException(Exception e, WebRequest request) {
    Exception responseException = e;
    String message = e.getMessage();
    if(!(e instanceof BookException)) {
      responseException = new BookException(DEFAULT_ERROR_MESSAGE);
    }
    return handleExceptionInternal(responseException, responseException, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

}
