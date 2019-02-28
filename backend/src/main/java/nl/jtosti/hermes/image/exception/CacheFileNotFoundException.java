package nl.jtosti.hermes.image.exception;

public class CacheFileNotFoundException extends RuntimeException {
    public CacheFileNotFoundException() {
        super("Uploaded file not found!");
    }
}
