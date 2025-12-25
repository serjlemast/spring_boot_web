package com.serjlemast1.controller;

import java.util.List;

public record UsersResponse(List<UserResponse> users,Integer total) {
    public UsersResponse(List<UserResponse> users) {
        this(users, users.size());
    }
}