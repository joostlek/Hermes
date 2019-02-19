package nl.jtosti.hermes.user.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.image.StorageServiceInterface;
import nl.jtosti.hermes.security.jwt.JwtTokenProvider;
import nl.jtosti.hermes.security.providers.UserAuthenticationProvider;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.auth.UserAuthServiceInterface;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserAuthController.class)
@DisplayName("User auth controller")
@Tag("Controller")
class UserAuthControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StorageServiceInterface storageService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserAuthenticationProvider authenticationProvider;

    @MockBean
    private UserAuthServiceInterface userAuthService;

    @Test
    @DisplayName("Add user")
    void shouldReturnUser_whenAddingUser() throws Exception {
        User user = new User("Alex", "Jones", "alex.jones@alex.com", "");
        user.setId(1L);
        User user1 = new User("Alex", "Jones", "alex.jones@alex.com", "");

        when(userAuthService.save(any(User.class))).thenReturn(user);

        mvc.perform(post("/auth/user/register")
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
}
