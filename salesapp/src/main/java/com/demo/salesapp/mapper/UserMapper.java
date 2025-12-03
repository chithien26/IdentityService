package com.demo.salesapp.mapper;

import com.demo.salesapp.dto.request.UserCreateRequest;
import com.demo.salesapp.dto.request.UserUpdateRequest;
import com.demo.salesapp.dto.response.UserCreateResponse;
import com.demo.salesapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface UserMapper {
    User toUser(UserCreateRequest userCreateRequest);
    User toUser(UserCreateResponse userCreateResponse);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
    UserCreateResponse toUserCreateResponse(User user);
}
