package com.serjlemast1.controller;

import com.serjlemast1.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserMapper userMapper;

  public UserController(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @GetMapping
  public ResponseEntity<UsersResponse> findAll() {
    return ResponseEntity.ok(userMapper.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> findById(@PathVariable("id") Integer id) {
    return userMapper
        .findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
    return ResponseEntity.ok(userMapper.create(userRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
    userMapper.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
