package nl.jtosti.hermes.screen.auth.exception;

public class ScreenPasswordExpiredException extends RuntimeException {
    public ScreenPasswordExpiredException() {
        super("Password expired");
    }
}
