package nl.jtosti.hermes.entities.dto;

public class ExtendedLocationDTO extends LocationDTO {
    private UserDTO owner;

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }
}
