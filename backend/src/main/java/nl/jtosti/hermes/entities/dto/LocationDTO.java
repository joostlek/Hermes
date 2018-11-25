package nl.jtosti.hermes.entities.dto;

import java.util.ArrayList;
import java.util.List;

public class LocationDTO {
    private Long id;
    private String name;
    private BasicUserDTO owner;
    private List<BasicScreenDTO> screens = new ArrayList<>();

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

    public List<BasicScreenDTO> getScreens() {
        return screens;
    }

    public void setScreens(List<BasicScreenDTO> screens) {
        this.screens = screens;
    }
}
