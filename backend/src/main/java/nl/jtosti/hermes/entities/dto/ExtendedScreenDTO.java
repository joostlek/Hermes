package nl.jtosti.hermes.entities.dto;

import nl.jtosti.hermes.entities.Screen;

public class ExtendedScreenDTO extends ScreenDTO {
    private LocationDTO location;

    public ExtendedScreenDTO(Screen screen) {
        super(screen);
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}
