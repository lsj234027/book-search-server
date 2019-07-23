package com.example.booksearchserver.infra.exception;

import java.io.Serializable;

public class BookException extends Exception {

  public BookException() {
  }

  public BookException(String message) {
    super(message);
  }
}
