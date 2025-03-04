package ru.itmentor.spring.boot_security.demo.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Short age;
    private Set<String> roles;
}
