package nl.jtosti.hermes.services;

import nl.jtosti.hermes.security.requests.ScreenAuthenticationRequest;
import nl.jtosti.hermes.security.requests.ScreenRegisterRequest;

public interface ScreenAuthServiceInterface {
    String authenticate(ScreenAuthenticationRequest request);

    String register(ScreenRegisterRequest request);
}
