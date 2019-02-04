package nl.jtosti.hermes.screen.exception;

public class ScreenNotFoundException extends RuntimeException {
    public ScreenNotFoundException(Long id) {
        super(String.format("Could not find screen %s", id));
    }
}
