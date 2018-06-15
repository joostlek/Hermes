package nl.jtosti.projects.hermes.models;

import nl.jtosti.projects.hermes.servlets.AuthenticationResource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<Location> locations;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<Image> images;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.PERSIST)
    private List<Promotion> promotions;

    public User() {
    }

    public User(String firstName, String middleName, String lastName, String email, String phoneNumber, String street, String houseNumber, String zipCode, String city, String country) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.locations = new ArrayList<>();
        this.images = new ArrayList<>();
        this.promotions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public void addImage(Image image) {
        this.images.add(image);
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public void addPromotion(Promotion promotion) {
        this.promotions.add(promotion);
    }

    public String getRole() {
        if (this.locations.size() > 1 && this.promotions.size() > 1) {
            return AuthenticationResource.ROLE_OWNER_AD;
        } else if (this.locations.size() > 1) {
            return AuthenticationResource.ROLE_ADVERTISING;
        } else if (this.promotions.size() > 1) {
            return AuthenticationResource.ROLE_OWNER;
        } else if (this.id == 1) {
            return AuthenticationResource.ROLE_SUPERUSER;
        } else {
            return AuthenticationResource.ROLE_USER;
        }
    }

    public String getFullName() {
        return this.middleName != null ? this.firstName + " "  + this.middleName + " " + this.lastName : this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return "<User " + this.getFullName() + ">";
    }
}
