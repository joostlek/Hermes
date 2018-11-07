package nl.jtosti.hermes.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "owner")
    private List<Location> locations = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    private List<Image> images = new ArrayList<>();

    protected User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.locations = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public User(String firstName, String lastName, List<Location> locations, List<Image> images) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.locations = locations;
        this.images = images;
    }

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

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "<User " + Long.toString(id) + ": " + firstName + " " + lastName + ">";
    }
}
