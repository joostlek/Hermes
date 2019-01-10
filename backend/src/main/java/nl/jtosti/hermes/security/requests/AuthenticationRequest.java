package nl.jtosti.hermes.security.requests;

public interface AuthenticationRequest {
    String getUsername();

    String getPassword();
}
