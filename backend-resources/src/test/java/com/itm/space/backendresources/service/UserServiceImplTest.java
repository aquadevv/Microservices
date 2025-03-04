package com.itm.space.backendresources.service;

import com.itm.space.backendresources.BaseIntegrationTest;
import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.api.response.UserResponse;
import com.itm.space.backendresources.exception.BackendResourcesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserServiceImplTest extends BaseIntegrationTest {

    @MockBean(answer = Answers.RETURNS_DEEP_STUBS)
    private Keycloak keycloakMock;

    @Autowired
    private UserService userService;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    private UserRequest testUserRequest;

    @BeforeEach
    void initializeUserRequest() {
        testUserRequest = new UserRequest(
                "testuser",
                "test@example.com",
                "securepass",
                "John",
                "Doe"
        );
    }

    @Test
    void shouldCreateUserSuccessfully() {
        Response mockedResponse = Response.status(Status.CREATED)
                .header("Location", "http://localhost/auth/admin/realms/" + keycloakRealm + "/users/456")
                .build();

        when(keycloakMock.realm(keycloakRealm).users().create(any(UserRepresentation.class)))
                .thenReturn(mockedResponse);

        assertDoesNotThrow(() -> userService.createUser(testUserRequest));
        verify(keycloakMock.realm(anyString()).users(), times(1))
                .create(any(UserRepresentation.class));
    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {
        Response errorResponse = Response.status(Status.BAD_REQUEST)
                .header("Location", "http://localhost/auth/admin/realms/" + keycloakRealm + "/users/789")
                .build();

        when(keycloakMock.realm(keycloakRealm).users().create(any(UserRepresentation.class)))
                .thenReturn(errorResponse);

        assertThrows(BackendResourcesException.class,
                () -> userService.createUser(testUserRequest));
        verify(keycloakMock.realm(anyString()).users(), times(1))
                .create(any(UserRepresentation.class));
    }

    @Test
    void shouldReturnUserWhenIdExists() {
        UUID userId = UUID.randomUUID();
        UserRepresentation mockUser = new UserRepresentation();
        mockUser.setId(userId.toString());
        mockUser.setEmail("user@example.com");
        mockUser.setFirstName("Jane");
        mockUser.setLastName("Smith");

        List<RoleRepresentation> mockRoles = List.of(
                new RoleRepresentation("MODERATOR", "Moderator role", false)
        );

        GroupRepresentation mockGroup = new GroupRepresentation();
        mockGroup.setName("Moderators");
        List<GroupRepresentation> mockGroups = List.of(mockGroup);

        when(keycloakMock.realm(keycloakRealm).users()
                .get(userId.toString()).toRepresentation())
                .thenReturn(mockUser);
        when(keycloakMock.realm(keycloakRealm).users()
                .get(userId.toString()).roles().getAll().getRealmMappings())
                .thenReturn(mockRoles);
        when(keycloakMock.realm(keycloakRealm).users()
                .get(userId.toString()).groups())
                .thenReturn(mockGroups);

        UserResponse result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(mockUser.getFirstName(), result.getFirstName());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        UUID nonExistentId = UUID.randomUUID();

        when(keycloakMock.realm(keycloakRealm).users()
                .get(nonExistentId.toString()).toRepresentation())
                .thenThrow(new BackendResourcesException(
                        "User not found",
                        HttpStatus.BAD_REQUEST
                ));

        BackendResourcesException thrown = assertThrows(
                BackendResourcesException.class,
                () -> userService.getUserById(nonExistentId)
        );

        assertEquals("User not found", thrown.getMessage());
    }
}