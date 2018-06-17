package nl.jtosti.projects.hermes.responses;

import nl.jtosti.projects.hermes.models.Location;
import nl.jtosti.projects.hermes.models.Screen;
import nl.jtosti.projects.hermes.models.Type;

import java.util.ArrayList;
import java.util.List;

public class LocationResponse {
    private int id;
    private String name;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;
    private UserResponse owner;
    private List<TypeResponse> types;
    private List<ScreenResponse> screens;

    public LocationResponse() {
    }

    public LocationResponse(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.street = location.getStreet();
        this.houseNumber = location.getHouseNumber();
        this.zipCode = location.getZipCode();
        this.city = location.getCity();
        this.country = location.getCountry();
        this.owner = location.getOwner().toResponse(true);
        this.types = new ArrayList<>();
        this.screens = new ArrayList<>();
        if (location.getTypes() != null) {
            for (Type type: location.getTypes()) {
                addType(type.toResponse(true));
            }
        }
        if (location.getScreens() != null) {
            for (Screen screen: location.getScreens()) {
                addScreen(screen.toResponse(true));
            }
        }
    }

    public LocationResponse(Location location, boolean simple) {
        this.id = location.getId();
        this.name = location.getName();
    }

    public void addType(TypeResponse typeResponse) {
        this.types.add(typeResponse);
    }

    public void addScreen(ScreenResponse screenResponse) {
        this.screens.add(screenResponse);
    }
}
