package nl.jtosti.hermes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    private final UserServiceInterface userService;

    @Autowired
    public LoginService(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return this.userService.getUserByEmail(email);
    }
}
