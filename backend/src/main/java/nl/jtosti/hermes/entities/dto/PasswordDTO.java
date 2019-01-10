package nl.jtosti.hermes.entities.dto;

public class PasswordDTO {
    private String password;

    public PasswordDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
