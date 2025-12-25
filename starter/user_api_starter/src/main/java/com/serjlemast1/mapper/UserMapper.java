package com.serjlemast1.mapper;

import com.serjlemast1.controller.UserRequest;
import com.serjlemast1.controller.UserResponse;
import com.serjlemast1.controller.UsersResponse;
import com.serjlemast1.service.UserRest;
import com.serjlemast1.service.UserRestService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
  private final UserRestService userService;

  public UserMapper(UserRestService userService) {
    this.userService = userService;
  }

  public UsersResponse findAll() {
    List<UserResponse> list =
        userService.findAll().stream()
            .map(u -> new UserResponse(u.id(), u.name(), u.email()))
            .toList();
    return new UsersResponse(list);
  }

  public Optional<UserResponse> findById(Integer id) {
    return userService.findById(id).map(u -> new UserResponse(u.id(), u.name(), u.email()));
  }

  public UserResponse create(UserRequest userRequest) {
    UserRest user = new UserRest(0, userRequest.username(), userRequest.email());
    UserRest save = userService.save(user);
    return new UserResponse(save.id(), save.name(), save.email());
  }

  public void deleteById(Integer id) {
    userService.delete(id);
  }
}
