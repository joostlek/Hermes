package nl.jtosti.hermes.entities.dto;

public class AuthUserDTO extends UserDTO {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
