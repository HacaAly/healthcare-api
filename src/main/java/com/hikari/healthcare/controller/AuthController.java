package com.hikari.healthcare.controller;

import com.hikari.healthcare.model.request.UserRegisterRequest;
import com.hikari.healthcare.model.response.UserResponse;
import com.hikari.healthcare.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody UserRegisterRequest request
            ){
        UserResponse response = userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
