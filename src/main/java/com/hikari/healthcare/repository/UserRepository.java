package com.hikari.healthcare.repository;

import com.hikari.healthcare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//menggunakan tipe generik User untuk data mana yang akan di manage dan Long untuk tipe data dari primary key data user
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existByUsername(String username);

    Boolean existByEmail(String email);
}
