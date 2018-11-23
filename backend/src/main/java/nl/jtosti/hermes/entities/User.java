package nl.jtosti.hermes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.jtosti.hermes.entities.dto.UserDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties({"owner", "screens"})
    private List<Location> locations = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    @JsonIgnoreProperties({"owner", "screen"})
    private List<Image> images = new ArrayList<>();

    protected User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.locations = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public User(String firstName, String lastName, String email, List<Location> locations, List<Image> images) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.locations = locations;
        this.images = images;
    }

    public User(UserDTO dto) {
        this.id = dto.getId();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    void addImage(Image image) {
        this.images.add(image);
    }

    @Override
    public String toString() {
        if (this.id == null) {
            return String.format("<User: %s %s>", this.firstName, this.lastName);
        } else {
            return String.format("<User %s: %s %s>", this.id, this.firstName, this.lastName);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return this.firstName.equals(user.getFirstName()) && this.lastName.equals(user.getLastName()) && Objects.equals(this.id, user.getId()) && this.email.equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
