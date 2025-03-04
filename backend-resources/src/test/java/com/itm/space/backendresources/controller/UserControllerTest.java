package com.itm.space.backendresources.controller;

import com.itm.space.backendresources.BaseIntegrationTest;
import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.api.response.UserResponse;
import com.itm.space.backendresources.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "moderator", roles = "MODERATOR")
    void hello_whenSuccessfully_ReturnUsername() throws Exception {
        mockMvc.perform(get("/api/users/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("moderator")
                );
    }

    @Test
    void hello_whenNotAuthenticated_ReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/api/users/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "moderator", roles = "MODERATOR")
    void getUserById_Success() throws Exception {
        UUID id = UUID.randomUUID();
        UserResponse userResponse = new UserResponse(
                "test",
                "testik",
                "test@example.com",
                List.of("MODERATOR"),
                List.of("Moderators")
        );

        when(userService.getUserById(id)).thenReturn(userResponse);

        mockMvc.perform(get("/api/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("test"))
                .andExpect(jsonPath("$.lastName").value("testik"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    void getUserById_whenNotAuthenticated_ReturnUnauthorized() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(get("/api/users/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "MODERATOR")
    void createUser_Success() throws Exception {
        UserRequest userRequest = new UserRequest(
                "username",
                "user@example.com",
                "password",
                "first name",
                "last name"
        );
        doNothing().when(userService).createUser(userRequest);
        mockMvc.perform(requestWithContent(post("/api/users"), userRequest))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(userRequest);
    }
}
