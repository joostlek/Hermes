package nl.jtosti.hermes.user.auth.dto;

public class AuthUserDTO {
    private String token;

    public AuthUserDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
