package com.hikari.healthcare.model.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.hikari.healthcare.common.exception.constant.RoleType;
import com.hikari.healthcare.entity.Role;
import com.hikari.healthcare.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//response class khususnya UserResponse class ini dengan bantuan helper method nya digunakan untuk mentransformasi data roles dan user
//sebagai response/balasan dari request yang masuk
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
    private Long userId;
    private String username;
    private String email;
    private boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<RoleType> roles;

    public static UserResponse fromUserAndRoles(User user, List<Role> roles) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                //melakukan iterasi di entity Role untuk menemukan nilai Type Role nya
                .roles(roles.stream()
                        .map(Role::getName)
                        .toList())
                .build();
    }
}
