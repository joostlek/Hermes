package nl.jtosti.hermes.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(String.format("Could not find user %s", id));
    }

    public UserNotFoundException(String email) {
        super(String.format("Could not find user linked to %s", email));
    }
}
