package nl.jtosti.hermes.entities.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<BasicLocationDTO> locations = new ArrayList<>();
    private List<BasicImageDTO> images = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BasicLocationDTO> getLocations() {
        return locations;
    }

    public void setLocations(List<BasicLocationDTO> locations) {
        this.locations = locations;
    }

    public List<BasicImageDTO> getImages() {
        return images;
    }

    public void setImages(List<BasicImageDTO> images) {
        this.images = images;
    }
}
