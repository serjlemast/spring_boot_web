package com.serjlemast1.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DogRepository {

    private final Map<Integer, Map<Integer, String>> map = Map.of(1, Map.of(1, "GavGav"));

    public String say(Integer userId, Integer petId) {
        return map.get(petId).get(userId);
    }
}
