package nl.jtosti.hermes.security.providers;

import nl.jtosti.hermes.entities.UserType;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProviderFactory implements FactoryBean<AuthenticationProvider> {
    private UserType userType;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private ScreenAuthenticationProvider screenAuthenticationProvider;

    @Override
    public AuthenticationProvider getObject() throws Exception {
        switch (userType) {
            case SCREEN:
                return this.screenAuthenticationProvider;
            case USER:
                return this.userAuthenticationProvider;
        }
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return AuthenticationProvider.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
