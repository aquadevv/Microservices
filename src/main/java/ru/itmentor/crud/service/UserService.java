package ru.itmentor.crud.service;

import ru.itmentor.crud.dto.UserDto;
import ru.itmentor.crud.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void createUser(UserDto userDto);

    void updateUser(Long userId, UserDto userDto);

    void deleteUser(Long userId);
}
