package nl.jtosti.hermes.user.auth;

import nl.jtosti.hermes.security.requests.UserAuthenticationRequest;
import nl.jtosti.hermes.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthServiceInterface {
    String authenticate(UserAuthenticationRequest request);

    String refreshAuthentication(UserDetails userDetails);

    User save(User user);

    User getUserByEmail(String email);
}
