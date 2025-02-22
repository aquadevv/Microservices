package ru.itmentor.crud.client;

import ru.itmentor.crud.dto.User;

import java.util.List;

public interface UserApiClient {
    List<User> getAllUsers();

    String createUser(User user);

    String updateUser(User user);

    String deleteUser(Long id);
}
