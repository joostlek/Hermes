package nl.jtosti.hermes.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordGeneratorTest {

    @Test
    void shouldReturnPassword() {
        assertThat(PasswordGenerator.generatePassword().length()).isEqualTo(20);
    }

}