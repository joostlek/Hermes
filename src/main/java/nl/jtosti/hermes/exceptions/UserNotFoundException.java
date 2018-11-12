package nl.jtosti.hermes.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("Could not find user %s", id));
    }
}
