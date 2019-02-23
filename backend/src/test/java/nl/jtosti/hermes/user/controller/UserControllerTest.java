package nl.jtosti.hermes.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.company.exception.CompanyNotFoundException;
import nl.jtosti.hermes.image.StorageServiceInterface;
import nl.jtosti.hermes.security.screen.ScreenAuthenticationProvider;
import nl.jtosti.hermes.security.user.UserAuthenticationProvider;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import nl.jtosti.hermes.user.dto.ExtendedUserDTO;
import nl.jtosti.hermes.user.exception.UserNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserAuthenticationProvider authenticationProvider;

    @MockBean
    private ScreenAuthenticationProvider screenAuthenticationProvider;

    @Test
    @DisplayName("Get all users")
    void shouldReturnJsonArray_whenGetAllUsers() throws Exception {
        User user = new User("Alex", "Jones", "alex.jones@alex.com", "");
        User user1 = new User("Jane", "Jones", "jane.jones@jones.com", "");
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
        User user = new User("Alex", "Jane", "alex.jones@alex.com", "");
        User user1 = new User("Jane", "Jones", "jane.jones@jones.com", "");
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
        User user = new User("Alex", "Jones", "alex.jones@alex.com", "");
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
    @DisplayName("Delete user")
    void shouldDoNothing_whenDeleteUser() throws Exception {
        mvc.perform(delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user"))
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get all users by company id")
    void shouldReturnJsonArray_whenGetAllUsersByCompanyId() throws Exception {
        User user = new User("Alex", "Jones", "alex.jones@alex.com", "");
        User user1 = new User("Jane", "Jones", "jane.jones@jones.com", "");
        List<User> allUsers = Arrays.asList(user, user1);
        given(service.getAllUsersByCompanyId(1L)).willReturn(allUsers);

        mvc.perform(get("/companies/1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$[1].firstName", is(user1.getFirstName())));
    }

    @Test
    @DisplayName("Get all users by invalid company id throws exception")
    void shouldThrowCompanyNotFoundException() throws Exception {

        when(service.getAllUsersByCompanyId(4L)).thenThrow(new CompanyNotFoundException(4L));

        mvc.perform(get("/companies/4/users")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Could not find company 4")));
    }

    @Test
    @DisplayName("Get user by unknown user id")
    void shouldThrowUserNotFoundException_whenGetInvalidUserId() throws Exception {

        when(service.getUserById(4L)).thenThrow(new UserNotFoundException(4L));

        mvc.perform(get("/users/4")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Could not find user 4")));
    }
}
