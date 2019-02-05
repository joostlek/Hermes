package nl.jtosti.hermes.screen.auth;

import nl.jtosti.hermes.screen.ScreenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ScreenLoginService implements UserDetailsService {

    private final ScreenServiceInterface screenService;

    @Autowired
    public ScreenLoginService(ScreenServiceInterface screenService) {
        this.screenService = screenService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        return screenService.getScreenById(Long.parseLong(s)).toUserDetails();
    }
}
