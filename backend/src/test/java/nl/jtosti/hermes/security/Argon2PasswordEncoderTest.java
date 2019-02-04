//package nl.jtosti.hermes.security;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Tag("Security")
//@DisplayName("Password Encoder")
//class Argon2PasswordEncoderTest {
//
//    private PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
//
//    @Test
//    void shouldReturnTrue_whenMatchedAgainstHashedPassword() {
//        String password = "test";
//        String hashedPassword = passwordEncoder.encode(password);
//        assertThat(hashedPassword).isNotEqualTo(password);
//        assertThat(passwordEncoder.matches(password, hashedPassword)).isTrue();
//        assertThat(passwordEncoder.matches("else", hashedPassword)).isFalse();
//    }
//
//}