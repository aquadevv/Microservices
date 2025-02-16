package ru.itmentor.crud.mapper;

import ru.itmentor.crud.dto.UserDto;
import ru.itmentor.crud.model.User;


public class UserMapper {
    private UserMapper() {
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        return user;
    }
}