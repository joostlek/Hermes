package nl.jtosti.hermes.security.requests;

import java.io.Serializable;

public class ScreenAuthenticationRequest implements Serializable, AuthenticationRequest {
    private Long screenId;
    private String password;

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    @Override
    public String getUsername() {
        return Long.toString(screenId);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
