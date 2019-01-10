package nl.jtosti.hermes.security;

public interface AuthenticationRequest {
    String getUsername();

    String getPassword();
}
