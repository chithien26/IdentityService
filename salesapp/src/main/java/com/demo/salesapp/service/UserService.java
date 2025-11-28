package com.demo.salesapp.service;

import com.demo.salesapp.dto.CreateUserRequest;
import com.demo.salesapp.dto.ApiResponse;
import com.demo.salesapp.entity.User;
import com.demo.salesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.demo.salesapp.dto.ApiResponse;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .email(createUserRequest.getEmail())
                .build();
        return userRepository.save(user);
    }
    public User getUserByUserId(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
