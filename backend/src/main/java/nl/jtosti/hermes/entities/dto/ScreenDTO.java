package nl.jtosti.hermes.entities.dto;

public class ScreenDTO {
    private Long id;
    private String name;
    private BasicLocationDTO location;

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

    public BasicLocationDTO getLocation() {
        return location;
    }

    public void setLocation(BasicLocationDTO location) {
        this.location = location;
    }
}
