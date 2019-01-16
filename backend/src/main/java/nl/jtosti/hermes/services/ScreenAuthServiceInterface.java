package nl.jtosti.hermes.services;

import nl.jtosti.hermes.security.requests.ScreenAuthenticationRequest;

public interface ScreenAuthServiceInterface {
    String authenticate(ScreenAuthenticationRequest request);
}
