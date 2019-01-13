package nl.jtosti.hermes.security.providers;

import nl.jtosti.hermes.entities.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Authentication provider factory")
@Tag("security")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AuthenticationProviderFactory.class)
class AuthenticationProviderFactoryTest {
    @Autowired
    private AuthenticationProviderFactory factory;

    @MockBean
    private UserAuthenticationProvider userProvider;

    @MockBean
    private ScreenAuthenticationProvider screenProvider;

    @Test
    void shouldWork() {
        assertThat(factory.getObject()).isNull();
        factory.setUserType(UserType.USER);
        AuthenticationProvider provider = factory.getObject();
        assertThat(provider).isInstanceOf(UserAuthenticationProvider.class);
        assertThat(factory.getUserType()).isEqualTo(UserType.USER);
        factory.setUserType(UserType.SCREEN);
        provider = factory.getObject();
        assertThat(provider).isInstanceOf(ScreenAuthenticationProvider.class);
        assertThat(factory.getUserType()).isEqualTo(UserType.SCREEN);
    }
}