package com.serjlemast1.service;

import java.util.List;
import java.util.Optional;

public interface UserRestService {

    List<UserRest> findAll();

    Optional<UserRest> findById(Integer id);

    UserRest save(UserRest user);

    void delete(Integer id);
}
