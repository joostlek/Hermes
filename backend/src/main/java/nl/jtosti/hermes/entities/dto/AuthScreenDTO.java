package nl.jtosti.hermes.entities.dto;

import nl.jtosti.hermes.entities.Screen;

public class AuthScreenDTO extends ScreenDTO {
    private String token;

    public AuthScreenDTO(Screen screen, String token) {
        super(screen);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
