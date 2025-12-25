package com.serjlemast1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public record UserRequest(String username, String email) {
  public UserRequest {
    if (username == null || username.isEmpty() || email == null || email.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Username or email is empty");
    }
  }
}
