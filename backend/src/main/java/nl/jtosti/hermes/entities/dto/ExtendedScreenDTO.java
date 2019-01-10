package nl.jtosti.hermes.entities.dto;

public class ExtendedScreenDTO extends ScreenDTO {
    private LocationDTO location;

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}
