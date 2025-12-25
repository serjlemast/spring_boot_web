package com.serjlemast2.service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);

    void delete(Integer id);
}
