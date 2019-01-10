package nl.jtosti.hermes.exceptions;

public class ScreenIsNotToReceivePasswordException extends RuntimeException {
    public ScreenIsNotToReceivePasswordException(Long screenId) {
        super(String.format("Screen %s is not set to receive password", screenId));
    }
}
