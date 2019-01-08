package nl.jtosti.hermes.exceptions;

public class NotIdentifiedAsUserException extends RuntimeException {
    public NotIdentifiedAsUserException(Long userId) {
        super(String.format("User not identified as given user %s", userId));
    }
}
