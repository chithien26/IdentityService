package com.demo.salesapp.service;

import com.demo.salesapp.dto.request.UserCreateRequest;
import com.demo.salesapp.dto.request.UserUpdateRequest;
import com.demo.salesapp.dto.response.UserCreateResponse;
import com.demo.salesapp.entity.User;
import com.demo.salesapp.exception.AppException;
import com.demo.salesapp.exception.ErrorCode;
import com.demo.salesapp.mapper.UserMapper;
import com.demo.salesapp.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
//@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserCreateResponse createUser(@Validated UserCreateRequest userCreateRequest) {
        if(userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new AppException(ErrorCode.DUPLICATE_USERNAME);
        }
        User user = userMapper.toUser(userCreateRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserCreateResponse(userRepository.save(user));
    }
    public UserCreateResponse updateUser(String user_id, @Valid UserUpdateRequest userUpdateRequest) {
        User user = userMapper.toUser(getUserByUserId(user_id));

        userMapper.updateUser(user, userUpdateRequest);
        return userMapper.toUserCreateResponse(userRepository.save(user));
    }

    public UserCreateResponse getUserByUserId(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserCreateResponse(user);
    }

    public String deleteAllUser() {
        userRepository.deleteAll();
        return "All users deleted successfully";
    }
}
