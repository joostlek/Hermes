package nl.jtosti.projects.hermes.models;

import nl.jtosti.projects.hermes.responses.LocationResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
@SequenceGenerator(name = "LOCATION_SEQ", sequenceName = "location_sequence")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCATION_SEQ")
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "houseNumber", nullable = false)
    private String houseNumber;

    @Column(name = "zipCode", nullable = false)
    private String zipCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(
            mappedBy = "location",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserLocation> users = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Type> types = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Screen> screens = new ArrayList<>();

    public Location() {
    }

    public Location(String name, String street, String houseNumber, String zipCode, String city, String country, User manager) {
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.addUser(manager, Roles.MANAGER);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<UserLocation> getUsers() {
        return users;
    }

    public void setUsers(List<UserLocation> users) {
        this.users = users;
    }

    public void addUser(User user, Roles role) {
        this.users.add(new UserLocation(user, this, role));
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public void addType(Type type) {
        this.types.add(type);
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public void addScreen(Screen screen) {
        this.screens.add(screen);
    }

    @Override
    public String toString() {
        return "<Location " + this.name + ">";
    }

    public LocationResponse toResponse(boolean simple) {
        return new LocationResponse(this, true);
    }

    public LocationResponse toResponse() {
        return new LocationResponse(this);
    }

    public User getOwner() {
        for (UserLocation userLocation : this.users) {
            if (userLocation.getRole().equals(Roles.MANAGER)) {
                return userLocation.getUser();
            }
        }
        return null;
    }
}
