package ru.itmentor.crud.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmentor.crud.client.UserApiClient;
import ru.itmentor.crud.dto.User;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserApiClient userApiClient;

    public String executeTask() {
        List<User> users = userApiClient.getAllUsers();
        log.info("Users found: {}", users);

        User newUser = new User(3L, "James", "Brown", (byte) 19);
        String partOne = userApiClient.createUser(newUser);

        User updateUser = new User(3L, "Thomas", "Shelby", (byte) 30);
        String partTwo = userApiClient.updateUser(updateUser);

        String partThree = userApiClient.deleteUser(3L);

        return partOne + partTwo + partThree;
    }
}
