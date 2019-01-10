package nl.jtosti.hermes.entities.dto;

import nl.jtosti.hermes.entities.User;

public class AuthUserDTO extends UserDTO {
    private String token;

    public AuthUserDTO(User user, String token) {
        super(user);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
