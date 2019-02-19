package nl.jtosti.hermes.user.auth;

import nl.jtosti.hermes.security.jwt.JwtTokenProvider;
import nl.jtosti.hermes.security.requests.UserAuthenticationRequest;
import nl.jtosti.hermes.user.User;
import nl.jtosti.hermes.user.UserServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserAuthService.class)
@DisplayName("User auth service")
@Tag("services")
class UserAuthServiceTest {

    @MockBean
    @Qualifier("userAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;

    @MockBean
    private UserServiceInterface userService;

    @MockBean
    private JwtTokenProvider provider;

    @Autowired
    private UserAuthService userAuthService;

    @Test
    @DisplayName("Authenticate user")
    void shouldReturnToken_whenAuthenticate() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com", "pass");
        user.setRoles(new ArrayList<>());

        UserAuthenticationRequest userAuthenticationRequest = new UserAuthenticationRequest();
        userAuthenticationRequest.setUsername("alex.jones@alex.com");
        userAuthenticationRequest.setPassword("pass");

        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(authenticationProvider.authenticate(any(Authentication.class))).thenReturn(null);
        when(provider.createToken(eq("alex.jones@alex.com"), any(List.class))).thenReturn("token");

        assertThat(userAuthService.authenticate(userAuthenticationRequest)).isEqualTo("token");
    }

    @Test
    @DisplayName("Authenticate user")
    void shouldThrowException_whenProvideBadCredentials() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com", "pass");
        user.setRoles(new ArrayList<>());

        UserAuthenticationRequest userAuthenticationRequest = new UserAuthenticationRequest();
        userAuthenticationRequest.setUsername("alex.jones@alex.com");
        userAuthenticationRequest.setPassword("pass");

        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(authenticationProvider.authenticate(any(Authentication.class))).thenThrow(new BadCredentialsException("rip"));
        when(provider.createToken(eq("alex.jones@alex.com"), any(List.class))).thenReturn("token");

        try {
            userAuthService.authenticate(userAuthenticationRequest);
            fail("User is authenticated");
        } catch (BadCredentialsException ex) {
            assertThat(ex.getMessage()).isEqualTo("Invalid username/password supplied");
        }
    }

    @Test
    @DisplayName("Refresh Token")
    void shouldReturnRefreshedToken_whenRefresh() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com", "pass");
        user.setRoles(new ArrayList<>());

        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        when(provider.createToken(eq("alex.jones@alex.com"), any(List.class))).thenReturn("token");

        assertThat(userAuthService.refreshAuthentication(user.toUserDetails())).isEqualTo("token");
    }

    @Test
    @DisplayName("Register User")
    void shouldSaveUser() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com", "pass");

        when(userService.save(any(User.class))).thenReturn(user);

        assertThat(userAuthService.save(user)).isEqualTo(user);
    }

    @Test
    @DisplayName("Get user by email")
    void shouldReturnUser_whenGiveEmail() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com", "pass");

        when(userService.getUserByEmail("alex.jones@alex.com")).thenReturn(user);

        assertThat(userAuthService.getUserByEmail("alex.jones@alex.com")).isEqualTo(user);
    }
}