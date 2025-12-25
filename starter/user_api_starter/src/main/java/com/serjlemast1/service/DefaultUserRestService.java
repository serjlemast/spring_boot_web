package com.serjlemast1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DefaultUserRestService implements UserRestService {
  
  @Override
  public List<UserRest> findAll() {
    return List.of();
  }

  @Override
  public Optional<UserRest> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public UserRest save(UserRest user) {
    throw new ResponseStatusException(HttpStatus.PARTIAL_CONTENT, "User not found");
  }

  @Override
  public void delete(Integer id) {
  }
}
