package com.demo.salesapp.service;

import com.demo.salesapp.dto.request.UserCreateRequest;
import com.demo.salesapp.dto.request.UserUpdateRequest;
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
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(@Validated UserCreateRequest userCreateRequest) {
        if(userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new AppException(ErrorCode.DUPLICATE_USERNAME);
        }
        User user = userMapper.toUser(userCreateRequest);
        return userRepository.save(user);
    }
    public User updateUser(String user_id, @Valid UserUpdateRequest userUpdateRequest) {
        User user = getUserByUserId(user_id);

        userMapper.updateUser(user, userUpdateRequest);
        return userRepository.save(user);
    }

    public User getUserByUserId(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
