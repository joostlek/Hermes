package nl.jtosti.hermes.screen.dto;

import nl.jtosti.hermes.location.dto.LocationDTO;

public class ExtendedScreenDTO extends ScreenDTO {
    private LocationDTO location;

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}
