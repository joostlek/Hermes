package nl.jtosti.hermes.security.screen;

public class ScreenPasswordExpiredException extends RuntimeException {
    public ScreenPasswordExpiredException() {
        super("Password expired");
    }
}
