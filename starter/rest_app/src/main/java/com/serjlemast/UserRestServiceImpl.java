package com.serjlemast;

import com.serjlemast2.service.User;
import com.serjlemast1.service.UserRest;
import com.serjlemast1.service.UserRestService;
import java.util.List;
import java.util.Optional;

import com.serjlemast2.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserRestServiceImpl implements UserRestService {

  private final UserService userService;

  public UserRestServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public List<UserRest> findAll() {
    return userService.findAll().stream().map(u -> new UserRest(u.id(), u.name(), u.email())).toList();
  }

  @Override
  public Optional<UserRest> findById(Integer id) {
    return userService.findById(id).map(u -> new UserRest(u.id(), u.name(), u.email()));
  }

  @Override
  public UserRest save(UserRest user) {
    User userRest = new User(user.id(), user.name(), user.email());
    User save = userService.save(userRest);
    return new UserRest(save.id(), save.name(), save.email());
  }

  @Override
  public void delete(Integer id) {
    userService.delete(id);
  }
}
