package com.demo.salesapp.controller;

import com.demo.salesapp.dto.ApiResponse;
import com.demo.salesapp.dto.CreateUserRequest;
import com.demo.salesapp.entity.User;
import com.demo.salesapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse<List<User>> getUsers() {
        return new ApiResponse<>(200, "Success", userService.getAllUsers());
    }

    @GetMapping("/{user_id}")
    public ApiResponse<User> getUserById(@PathVariable("user_id") String userId) {
        return new ApiResponse<>(200, "Success", userService.getUserByUserId(userId));
    }

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return new ApiResponse<>(201, "User Created", userService.createUser(createUserRequest));
    }
}
