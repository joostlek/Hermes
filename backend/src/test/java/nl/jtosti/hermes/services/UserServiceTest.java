package nl.jtosti.hermes.services;

import nl.jtosti.hermes.entities.User;
import nl.jtosti.hermes.exceptions.UserNotFoundException;
import nl.jtosti.hermes.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DisplayName("User Service")
@Tag("services")
class UserServiceTest {

    @Autowired
    private UserServiceInterface userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Get user with valid email")
    void shouldReturnUser_whenGetUserWithEmail() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        User found = userService.getUserByEmail(user.getEmail());
        assertThat(found).isEqualTo(user);
    }

    @Test
    @DisplayName("Get all users")
    void shouldReturnUsers_whenGetAllUsers() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        User user1 = new User("Jay", "Jones", "jay.jones@jay.com");
        given(userRepository.findAllByOrderByIdAsc()).willReturn(Arrays.asList(user, user1));

        List<User> found = userService.getAllUsers();
        assertThat(found).hasSize(2);
        assertThat(found.get(0)).isEqualTo(user);
        assertThat(found.get(1)).isEqualTo(user1);
    }

    @Test
    @DisplayName("Add user")
    void shouldReturnNewUser_whenAddingUser() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User newUser = userService.save(user);
        assertThat(newUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("User exists")
    void shouldReturnTrue_whenEmailExists() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        assertThat(userService.exists(user.getEmail())).isTrue();
    }

    @Test
    @DisplayName("User doesn't exist")
    void shouldReturnFalse_whenEmailDoesNotExist() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

        assertThat(userService.exists(user.getEmail())).isFalse();
    }

    @Test
    @DisplayName("Get single user")
    void shouldReturnUser_whenGetSingleUser() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertThat(userService.getUserById(1L).getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("Get invalid user throws exception")
    void shouldThrowException_whenGetUserWithInvalidId() {
        try {
            userService.getUserById(2L);
//          Making sure the test actually fails if it returned something
            assertThat(true).isFalse();
        } catch (UserNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find user 2");
        }
    }

    @Test
    @DisplayName("Update invalid user throws exception")
    void shouldThrowException_whenUpdateUserWithInvalidId() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        user.setId(2L);
        try {
            userService.updateUser(user);
//          Making sure the test actually fails if it returned something
            assertThat(true).isFalse();
        } catch (UserNotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Could not find user 2");
        }
    }

    @Test
    @DisplayName("Update user")
    void shouldReturnUpdatedUser_whenUpdateUser() {
        User user = new User("Alex", "Jones", "alex.jones@alex.com");
        User user1 = new User("Jay", "Jones", "jay.jones@jay.com");

        user.setId(1L);
        user1.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(user1)).thenReturn(user1);

        assertThat(userService.updateUser(user1)).isEqualTo(user1);
    }

    @Test
    @DisplayName("Delete user")
    void shouldDoNothing_whenDeleteUser() {
        userService.deleteUser(1L);
    }


    @TestConfiguration
    static class UserServiceTestContextConfiguration {
        @Bean
        public UserServiceInterface userServiceInterface() {
            return new UserService();
        }
    }

}
