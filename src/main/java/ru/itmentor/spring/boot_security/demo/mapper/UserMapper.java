package ru.itmentor.spring.boot_security.demo.mapper;

import org.springframework.stereotype.Component;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.model.User;

@Component
public class UserMapper {
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        return user;
    }
}