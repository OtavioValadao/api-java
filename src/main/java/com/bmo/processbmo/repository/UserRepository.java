package com.bmo.processbmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.bmo.processbmo.model.domain.User.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    UserDetails findByLogin(String username);
    
}
