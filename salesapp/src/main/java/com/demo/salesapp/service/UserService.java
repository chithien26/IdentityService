package com.demo.salesapp.service;

import com.demo.salesapp.dto.request.CreateUserRequest;
import com.demo.salesapp.entity.User;
import com.demo.salesapp.exception.AppException;
import com.demo.salesapp.exception.ErrorCode;
import com.demo.salesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(@Validated CreateUserRequest createUserRequest) {
        if(userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new AppException(ErrorCode.DUPLICATE_USERNAME);
        }
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
