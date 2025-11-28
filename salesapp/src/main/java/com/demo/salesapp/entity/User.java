package com.demo.salesapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.AccessType;

import java.util.logging.Level;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String username;
    String password;
    String email;
    String phone;
    String firstName;
    String lastName;
}
