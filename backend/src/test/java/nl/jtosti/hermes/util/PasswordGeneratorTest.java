package nl.jtosti.hermes.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Password Generator")
@Tag("Util")
class PasswordGeneratorTest {

    @Test
    @DisplayName("Generated password is 20 characters long")
    void shouldReturnPasswordWith20Characters_whenGeneratePassword() {
        assertThat(PasswordGenerator.generatePassword().length()).isEqualTo(20);
    }

    @Test
    @DisplayName("Throw exception when generated password is less than 4")
    void shouldThrowIllegalArgumentException_whenLengthIsLessThan4() {
        try {
            PasswordGenerator.getPassword(4);
            assertThat(true).isFalse();
        } catch (IllegalArgumentException ex) {
            assertThat(ex.getMessage()).isEqualTo("Password length must be bigger than 4");
        }
    }

}