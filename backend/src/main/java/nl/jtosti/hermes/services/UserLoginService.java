package nl.jtosti.hermes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserServiceInterface userService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        return this.userService.getUserByEmail(s).toUserDetails();
    }
}
