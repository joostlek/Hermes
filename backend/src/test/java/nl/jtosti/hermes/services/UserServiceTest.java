package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.exceptions.UserNotFoundException;
import nl.jtosti.hermes.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserServiceInterface userServiceInterface;
    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        User user1 = new User("Jay", "Jones", "jay.jones@jay.com");
        user1.setId(1L);
        MockitoAnnotations.initMocks(this);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        when(userRepository.findAll()).thenReturn(Arrays.asList(user, user1));
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
    }

    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User found = userServiceInterface.getUserByEmail(user.getEmail());
        assertThat(found).isEqualTo(user);
    }

    @Test
    public void whenGetAllUsers() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        User user1 = new User("Jay", "Jones", "jay.jones@jay.com");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user, user1));

        List<User> found = userServiceInterface.getAllUsers();
        assertThat(found).hasSize(2);
        assertThat(found.get(0)).isEqualTo(user);
        assertThat(found.get(1)).isEqualTo(user1);
    }

    @Test
    public void whenSaveUser_thenReturnUser() {
        User user = userServiceInterface.save(new User("Alex", "Jones", "alex.jones@alex.com"));
        assertThat(user.getEmail()).isEqualTo("alex.jones@alex.com");
    }

    @Test
    public void whenUserExists_thenReturnTrue() {
        String email = "alex.jones@alex.com";
        assertThat(userServiceInterface.exists(email)).isTrue();
        assertThat(userServiceInterface.exists("jay.jones@alex.com")).isFalse();
    }

    @Test
    public void whenGivenUserId_thenReturnUser() {
        Long id = 1L;
        String email = "jay.jones@jay.com";
        assertThat(userServiceInterface.getUserById(id).getEmail()).isEqualTo(email);
    }

    @Test
    public void whenGivenInvalidId_shouldThrowError() {
        try {
            userServiceInterface.getUserById(2L);
//          Making sure the test actually fails if it returned something
            assertThat(true).isFalse();
        } catch (UserNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find user 2");
        }
    }

    @Test
    public void whenUpdatingInvalidId_shouldThrowError() {
        try {
            userServiceInterface.updateUser(new User("Alex", "Jones", "alex.jones@alex.com"), 2L);
//          Making sure the test actually fails if it returned something
            assertThat(true).isFalse();
        } catch (UserNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find user 2");
        }
    }

    @Test
    public void whenUpdatingUser_thenReturnUpdatedUser() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        User user1 = new User("Jay", "Jones", "jay.jones@jay.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user1)).thenReturn(user1);

        assertThat(userServiceInterface.updateUser(user1, 1L)).isEqualTo(user1);
    }

    @Test
    public void whenDeletingUser_doNothing() {
        userServiceInterface.deleteUser(1L);
    }


    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserServiceInterface userService() {
            return new UserService();
        }
    }

}
