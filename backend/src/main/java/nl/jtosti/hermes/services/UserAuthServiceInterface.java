package nl.jtosti.hermes.services;

import nl.jtosti.hermes.security.requests.UserAuthenticationRequest;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthServiceInterface {
    String authenticate(UserAuthenticationRequest request);

    String refreshAuthentication(UserDetails userDetails);
}
