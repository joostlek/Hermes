package nl.jtosti.hermes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.entities.dto.ExtendedUserDTO;
import nl.jtosti.hermes.services.StorageServiceInterface;
import nl.jtosti.hermes.services.UserServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@DisplayName("User Controller")
@Tag("Controller")
class UserControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceInterface service;

    @MockBean
    private StorageServiceInterface storageService;

    @Test
    @DisplayName("Get all users")
    void shouldReturnJsonArray_whenGetAllUsers() throws Exception {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        User user1 = new User("Jane", "Jones", "jane.jones@jones.com");
        List<User> allUsers = Arrays.asList(user, user1);
        given(service.getAllUsers()).willReturn(allUsers);

        mvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$[1].firstName", is(user1.getFirstName())));
    }

    @Test
    @DisplayName("Update user")
    void shouldReturnUpdatedUser_whenUpdateUser() throws Exception {
        User user = new User("Alex", "Jane", "alex.jones@alex.com");
        User user1 = new User("Jane", "Jones", "jane.jones@jones.com");
        user.setId(1L);

        when(service.updateUser(any(User.class))).thenReturn(user1);

        mvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writer().writeValueAsString(user1))
                .with(user("user"))
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writer().writeValueAsString(modelMapper.map(user1, ExtendedUserDTO.class))));
    }

    @Test
    @DisplayName("Get single user")
    void shouldReturnUser_whenGetSingleUser() throws Exception {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        user.setId(1L);

        when(service.getUserById(1L)).thenReturn(user);

        mvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }

    @Test
    @DisplayName("Add user")
    void shouldReturnUser_whenAddingUser() throws Exception {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        user.setId(1L);
        User user1 = new User("Alex", "Jones", "alex.jones@alex.com");

        when(service.save(any(User.class))).thenReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writer().writeValueAsString(user1))
                .with(user("user"))
                .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }

    @Test
    @DisplayName("Delete user")
    void shouldDoNothing_whenDeleteUser() throws Exception {
        mvc.perform(delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user"))
                .with(csrf()))
                .andExpect(status().isOk());
    }
}
