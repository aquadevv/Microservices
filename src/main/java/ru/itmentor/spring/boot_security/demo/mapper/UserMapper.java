package ru.itmentor.spring.boot_security.demo.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.dto.UserCreateDto;
import ru.itmentor.spring.boot_security.demo.dto.UserResponse;
import ru.itmentor.spring.boot_security.demo.dto.UserUpdateDto;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    public User toEntity(UserCreateDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setRoles(roleMapper.mapRoleNamesToEntities(dto.getRoles()));
        return user;
    }

    public User updateFromDto(UserUpdateDto dto, User user) {
        if (dto.getUsername() != null) user.setUsername(dto.getUsername());
        if (dto.getPassword() != null) user.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getAge() != null) user.setAge(dto.getAge());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());

        if (dto.getRoles() != null) {
            user.setRoles(roleMapper.mapRoleNamesToEntities(dto.getRoles()));
        }
        return user;
    }

    public UserResponse toDto(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setAge(user.getAge());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoles().stream()
            .map(Role::getName)
            .collect(Collectors.toSet()));
        return response;
    }
}