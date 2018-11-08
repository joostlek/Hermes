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
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Location> locations = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return this.firstName.equals(user.getFirstName()) && this.lastName.equals(user.getLastName()) && this.id.equals(user.getId()) && this.email.equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
