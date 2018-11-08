package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
    }

    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        String email = "alex.jones@alex.com";
        User found = userService.getUserByEmail(email);
        assertThat(found.getEmail()).isEqualTo(email);
    }

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

}