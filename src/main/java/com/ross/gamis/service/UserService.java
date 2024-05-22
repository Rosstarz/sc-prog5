package com.ross.gamis.service;

import org.springframework.stereotype.Service;

import com.ross.gamis.domain.User;
import com.ross.gamis.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
