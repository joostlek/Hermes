package nl.jtosti.hermes.exceptions;

public class ScreenNotFoundException extends RuntimeException {
    public ScreenNotFoundException(Long id) {
        super(String.format("Could not find screen %s", id));
    }
}
