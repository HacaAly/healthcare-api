package com.hikari.healthcare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
//class konfigurasi digunakan untuk menyetting class konfigurasi yang sudah ada namun kita set seperti apa yang ingin kita ingin gunakan
    @Bean
    public PasswordEncoder passwordEncoder() {
        //membuat password encoder untuk melakukan hashing atau enskripsi
        //yaitu dengan algoritma BCrypt
        return new BCryptPasswordEncoder();
    }
}
