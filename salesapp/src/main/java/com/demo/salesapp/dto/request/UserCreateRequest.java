package com.demo.salesapp.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    String username;
    @Size(min = 8, max = 30)
    String password;
    @Email
    String email;
    String phone;
    String firstName;
    String lastName;
}
