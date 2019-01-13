package nl.jtosti.hermes.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.jtosti.hermes.security.jwt.JwtTokenProvider;
import nl.jtosti.hermes.security.providers.AuthenticationProviderFactory;
import nl.jtosti.hermes.security.providers.ScreenAuthenticationProvider;
import nl.jtosti.hermes.security.providers.UserAuthenticationProvider;
import nl.jtosti.hermes.services.ScreenServiceInterface;
import nl.jtosti.hermes.services.StorageServiceInterface;
import nl.jtosti.hermes.services.UserServiceInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
@ContextConfiguration(classes = AuthenticationProviderFactory.class)
@DisplayName("Authentication Controller")
@Tag("Controller")
class AuthControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private StorageServiceInterface storageService;

    @MockBean
    private UserServiceInterface userService;

    @MockBean
    private ScreenServiceInterface screenService;

    @Autowired
    private AuthenticationProviderFactory authenticationProviderFactory;

    @MockBean
    private UserAuthenticationProvider userAuthenticationProvider;

    @MockBean
    private ScreenAuthenticationProvider screenAuthenticationProvider;

    @Test
    void test() {
        assertTrue(true);
    }

}