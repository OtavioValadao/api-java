package com.bmo.processbmo.view.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmo.processbmo.model.User;
import com.bmo.processbmo.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository repository){
        this.userRepository = repository;
    }

    @GetMapping("/listUsers")
    public ResponseEntity<List<User>> listUsers(){
        
        return ResponseEntity.ok(userRepository.findAll());
    }

}