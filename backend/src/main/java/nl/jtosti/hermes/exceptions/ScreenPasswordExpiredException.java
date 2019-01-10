package nl.jtosti.hermes.exceptions;

public class ScreenPasswordExpiredException extends RuntimeException {
    public ScreenPasswordExpiredException() {
        super("Password expired");
    }
}
