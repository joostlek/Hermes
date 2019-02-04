package nl.jtosti.hermes.image.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(Long id) {
        super(String.format("Could not find image %s", id));
    }
}
