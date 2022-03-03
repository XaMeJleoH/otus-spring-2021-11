package ru.otus.spring.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface MongoUserDetailsService {
    UserDetails loadUserByUsername(String username);
}
