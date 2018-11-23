package nl.jtosti.hermes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.services.UserServiceInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserServiceInterface service;

    @Test
    public void givenUsers_whenGetUsers_thenReturnJsonArray() throws Exception {
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
    public void givenUser_whenUpdatingUser_thenReturnUpdatedUser() throws Exception {
        User user = new User("Alex", "Jane", "alex.jones@alex.com");
        User user1 = new User("Jane", "Jones", "jane.jones@jones.com");
        user.setId(1L);

        when(service.updateUser(any(User.class))).thenReturn(user);

        mvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writer().writeValueAsString(user1))
                .with(user("user"))
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writer().writeValueAsString(user)));
    }

    @Test
    public void givenUserId_whenGetUser_thenReturnUser() throws Exception {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        user.setId(1L);

        when(service.getUserById(1L)).thenReturn(user);

        mvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.id", is(user.getId().intValue())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.images", hasSize(0)))
                .andExpect(jsonPath("$.locations", hasSize(0)))
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }

    @Test
    public void givenNewUser_thenReturnSavedUser() throws Exception {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        user.setId(1L);

        when(service.save(any(User.class))).thenReturn(user);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n" +
                        "    \"firstName\": \"Joost\",\n" +
                        "    \"lastName\": \"Kekkerkerker\",\n" +
                        "    \"email\": \"\" \n" +
                        "}")
                .with(user("user"))
                .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    public void givenUserId_thenDeleteUser() throws Exception {
        mvc.perform(delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user"))
                .with(csrf()))
                .andExpect(status().isOk());
    }
}
