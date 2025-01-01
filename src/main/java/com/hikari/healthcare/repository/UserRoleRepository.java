package com.hikari.healthcare.repository;

import com.hikari.healthcare.entity.UserRole;
import com.hikari.healthcare.entity.UserRole.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    void deleteByIdUserId(Long userId);
}
