package nl.jtosti.hermes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ScreenLoginService implements UserDetailsService {

    @Autowired
    private ScreenServiceInterface screenService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return screenService.getScreenById(Long.parseLong(s));
    }
}
