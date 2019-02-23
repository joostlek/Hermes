package nl.jtosti.hermes.security.user;

import nl.jtosti.hermes.user.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    private final UserServiceInterface userService;

    @Autowired
    public UserLoginService(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return this.userService.getUserByEmail(s).toUserDetails();
    }
}
