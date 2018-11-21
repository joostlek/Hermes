package nl.jtosti.hermes.advices;

import nl.jtosti.hermes.exceptions.ImageNotFoundException;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ImageNotFoundAdviceTest {
    @Test
    public void testThrow() {
        ImageNotFoundAdvice imageNotFoundAdvice = new ImageNotFoundAdvice();
        ImageNotFoundException exception = new ImageNotFoundException(1L);
        try {
            throw exception;
        } catch (ImageNotFoundException ex) {
            assertThat(imageNotFoundAdvice.imageNotFoundHandler(ex)).isEqualTo("Could not find image 1");
        }
    }

}