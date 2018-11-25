package nl.jtosti.hermes.entities.dto;

public class ExtendedLocationDTO extends LocationDTO {
    private BasicUserDTO owner;

    public BasicUserDTO getOwner() {
        return owner;
    }

    public void setOwner(BasicUserDTO owner) {
        this.owner = owner;
    }
}
