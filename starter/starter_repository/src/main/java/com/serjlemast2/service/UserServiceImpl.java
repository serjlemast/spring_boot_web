package com.serjlemast2.service;

import com.serjlemast2.repository.UserEntity;
import com.serjlemast2.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAllUsers().stream()
        .map(entity -> new User(entity.id(), entity.name(), entity.email()))
        .toList();
  }

  @Override
  public Optional<User> findById(Integer id) {
    return userRepository
        .findUserById(id)
        .map(entity -> new User(entity.id(), entity.name(), entity.email()));
  }

  @Override
  public User save(User user) {
    UserEntity userEntity = new UserEntity(user.id(), user.name(), user.email());
    UserEntity persistUser = userRepository.createUser(userEntity);
    return new User(persistUser.id(), persistUser.name(), persistUser.email());
  }

  @Override
  public void delete(Integer id) {
    userRepository.deleteUser(id);
  }
}
