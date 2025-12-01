package com.demo.salesapp.controller;

import com.demo.salesapp.dto.request.UserUpdateRequest;
import com.demo.salesapp.dto.response.ApiResponse;
import com.demo.salesapp.dto.request.UserCreateRequest;
import com.demo.salesapp.entity.User;
import com.demo.salesapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ApiResponse<User> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest) {
        return new ApiResponse<>(201, "User Created", userService.createUser(userCreateRequest));
    }

    @PatchMapping("/{user_id}")
    public  ApiResponse<User> updateUser(@PathVariable("user_id") String user_id, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        return  new ApiResponse<>(200, "User Updated", userService.updateUser(user_id, userUpdateRequest));
    }
}
