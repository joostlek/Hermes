//package nl.jtosti.hermes.advices;
//
//import nl.jtosti.hermes.image.advice.ImageNotFoundAdvice;
//import nl.jtosti.hermes.image.exception.ImageNotFoundException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Tag;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//
//@DisplayName("Image not found advice")
//@Tag("Advices")
//class ImageNotFoundAdviceTest {
//    @Test
//    void testThrow() {
//        ImageNotFoundAdvice imageNotFoundAdvice = new ImageNotFoundAdvice();
//        ImageNotFoundException exception = new ImageNotFoundException(1L);
//        try {
//            throw exception;
//        } catch (ImageNotFoundException ex) {
//            assertThat(imageNotFoundAdvice.imageNotFoundHandler(ex).getMessage()).isEqualTo("Could not find image 1");
//        }
//    }
//
//}