package nl.jtosti.projects.hermes.models;

import nl.jtosti.projects.hermes.responses.LocationResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "location", cascade = CascadeType.PERSIST)
    private List<Type> types;

    @OneToMany(mappedBy = "location", cascade = CascadeType.PERSIST)
    private List<Screen> screens;

    public Location() {
    }

    public Location(String name, String street, String houseNumber, String zipCode, String city, String country, User owner) {
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.owner = owner;
        this.types = new ArrayList<>();
        this.screens = new ArrayList<>();
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
}
