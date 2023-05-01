package com.bmo.processbmo.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.bmo.processbmo.model.domain.User.User;
import com.bmo.processbmo.model.exception.ResourceNotFoundException;
import com.bmo.processbmo.repository.UserRepository;
import com.bmo.processbmo.shared.UserDTO;


public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public Optional<UserDTO> getUserbyId(Integer id){

        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty()){
            throw new ResourceNotFoundException("Usuário com id: " + id + " não encontrado");
        }

        UserDTO userDTO = new ModelMapper().map(user.get(), UserDTO.class);

        return Optional.of(userDTO);
    }


    public UserDTO login(String userEmail, String userPassword){
        
        return null;
    }
}
