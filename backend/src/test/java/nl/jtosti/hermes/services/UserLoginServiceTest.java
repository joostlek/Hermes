//package nl.jtosti.hermes.services;
//
//import nl.jtosti.hermes.user.auth.ApplicationUser;
//import nl.jtosti.hermes.company.Company;
//import nl.jtosti.hermes.user.User;
//import nl.jtosti.hermes.user.exception.UserNotFoundException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = UserLoginService.class)
//@DisplayName("User login service")
//@Tag("services")
//class UserLoginServiceTest {
//
//    @Qualifier("userLoginService")
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @MockBean
//    private UserServiceInterface userService;
//
//    private Company user = new User("Alex", "Jones", "Alex.jones@alex.com");
//
//    @Test
//    void shouldReturnUserDetails_whenGetUserByEmail() {
//        user.setId(1L);
//        user.setPassword("password");
//
//        when(userService.getUserByEmail("Alex.jones@alex.com")).thenReturn(user);
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername("Alex.jones@alex.com");
//        assertThat(userDetails).isInstanceOf(ApplicationUser.class);
//        assertThat(userDetails.getUsername()).isEqualTo(user.getEmail());
//        assertThat(userDetails.getPassword()).isEqualTo(user.getPassword());
//        assertThat(userDetails.isCredentialsNonExpired()).isTrue();
//        assertThat(userDetails.isEnabled()).isTrue();
//        assertThat(userDetails.isAccountNonExpired()).isTrue();
//        assertThat(userDetails.isAccountNonLocked()).isTrue();
//
//    }
//
//    @Test
//    void shouldThrowUserNotFoundException_whenGetUnknownUserId() {
//        when(userService.getUserByEmail("Jane.jones@alex.com")).thenThrow(new UserNotFoundException("Jane.jones@alex.com"));
//
//        try {
//            userDetailsService.loadUserByUsername("Jane.jones@alex.com");
//            assertThat(true).isFalse();
//        } catch (UserNotFoundException ex) {
//            assertThat(ex.getMessage()).isEqualTo("Could not find user linked to Jane.jones@alex.com");
//        }
//    }
//
//
//}