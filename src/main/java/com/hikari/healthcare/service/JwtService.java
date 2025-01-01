package com.hikari.healthcare.service;

import com.hikari.healthcare.model.UserInfo;

public interface JwtService {
    String generateToken(UserInfo userInfo);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}
