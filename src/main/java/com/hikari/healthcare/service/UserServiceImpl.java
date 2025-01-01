package com.hikari.healthcare.service;

import com.hikari.healthcare.common.exception.*;
import com.hikari.healthcare.common.exception.constant.RoleType;
import com.hikari.healthcare.entity.Role;
import com.hikari.healthcare.entity.User;
import com.hikari.healthcare.entity.UserRole;
import com.hikari.healthcare.entity.UserRole.UserRoleId;
import com.hikari.healthcare.model.request.UserRegisterRequest;
import com.hikari.healthcare.model.response.UserResponse;
import com.hikari.healthcare.repository.RoleRepository;
import com.hikari.healthcare.repository.UserRepository;
import com.hikari.healthcare.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserResponse registerUser(UserRegisterRequest userRegisterRequest) {
        if (existByEmail(userRegisterRequest.getEmail())) {
            throw new EmailAlreadyExistException("Email " + userRegisterRequest.getEmail() + " is already taken");
        }

        if (existByUsername(userRegisterRequest.getUsername())) {
            throw new UsernameAlreadyExistException("Username " + userRegisterRequest.getUsername() + " is already taken");
        }

        if (!userRegisterRequest.getPassword().equals(userRegisterRequest.getPasswordConfirmation())) {
            throw new BadRequestException("Password is not matched");
        }

        User user = User.builder()
                .username(userRegisterRequest.getUsername())
                .email(userRegisterRequest.getEmail())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .enabled(true)
                .build();
        userRepository.save(user);

        Role role = roleRepository.findByName(RoleType.PATIENT)
                .orElseThrow(() -> new RoleNotFoundException("Role is not found"));

        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(user.getUserId(), role.getRoleId()))
                .build();
        userRoleRepository.save(userRole);

        return UserResponse.fromUserAndRoles(user, List.of(role));
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id :" + id + "not found"));
        List<Role> roles = roleRepository.findByUserId(id);

        return UserResponse.fromUserAndRoles(user, roles);
    }

    @Override
    public UserResponse getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username :" + username + "not found"));
        List<Role> roles = roleRepository.findByUserId(user.getUserId());

        return UserResponse.fromUserAndRoles(user, roles);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existByEmail(email);
    }
}
