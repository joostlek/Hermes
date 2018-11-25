package nl.jtosti.hermes.entities.dto;

public class LocationDTO {
    private Long id;
    private String name;
    private BasicUserDTO owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BasicUserDTO getOwner() {
        return owner;
    }

    public void setOwner(BasicUserDTO owner) {
        this.owner = owner;
    }
}
