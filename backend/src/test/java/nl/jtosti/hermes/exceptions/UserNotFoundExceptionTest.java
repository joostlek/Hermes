//package nl.jtosti.hermes.exceptions;
//
//
//import nl.jtosti.hermes.user.exception.UserNotFoundException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//
//@DisplayName("User not found exception")
//@Tag("Exceptions")
//class UserNotFoundExceptionTest {
//
//    @Test
//    void testThrow() {
//        UserNotFoundException exception = new UserNotFoundException(1L);
//        try {
//            throw exception;
//        } catch (UserNotFoundException ex) {
//            assertThat(ex.getMessage()).isEqualTo("Could not find user 1");
//        }
//    }
//
//}
