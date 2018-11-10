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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        User user1 = new User("Jay", "Jones", "jay.jones@jay.com");
        user1.setId(1L);

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user, user1));
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
    }

    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        String email = "alex.jones@alex.com";
        User found = userService.getUserByEmail(email);
        assertThat(found.getEmail()).isEqualTo(email);
    }

    @Test
    public void whenGetAllUsers() {
        List<User> found = userService.getAllUsers();
        assertThat(found).hasSize(2);
    }

    @Test
    public void whenSaveUser_thenReturnUser() {
        User user = userService.save(new User("Alex", "Jones", "alex.jones@alex.com"));
        assertThat(user.getEmail()).isEqualTo("alex.jones@alex.com");
    }

    @Test
    public void whenUserExists_thenReturnTrue() {
        String email = "alex.jones@alex.com";
        assertThat(userService.exists(email)).isTrue();
        assertThat(userService.exists("jay.jones@alex.com")).isFalse();
    }

    @Test
    public void whenGivenUserId_thenReturnUser() {
        Long id = 1L;
        String email = "jay.jones@jay.com";
        assertThat(userService.getUserById(id).getEmail()).isEqualTo(email);
    }

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

}
