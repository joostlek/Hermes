package nl.jtosti.hermes.entities.dto;

public class AuthScreenDTO {
    private String token;

    public AuthScreenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
