package com.serjlemast1.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CatRepository {

    private final Map<Integer,String> map = Map.of(1,"MayMay");

    public Map<Integer,String> say(Integer id) {
        return map;
    }
}
