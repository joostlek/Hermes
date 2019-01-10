package nl.jtosti.hermes.entities.dto;

public class AuthScreenDTO extends ScreenDTO {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
