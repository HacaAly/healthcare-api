package com.hikari.healthcare.service;

import com.hikari.healthcare.model.request.UserRegisterRequest;
import com.hikari.healthcare.model.response.UserResponse;

public interface UserService {
    UserResponse registerUser(UserRegisterRequest userRegisterRequest);
    UserResponse getUserById(Long id);
    UserResponse getByUsername(String username);
    boolean existByUsername(String username);
    boolean existByEmail(String email);
}
