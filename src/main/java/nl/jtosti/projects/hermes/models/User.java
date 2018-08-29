package nl.jtosti.projects.hermes.models;

import nl.jtosti.projects.hermes.responses.UserResponse;
import nl.jtosti.projects.hermes.resourcesv1.AuthenticationResource;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "user_sequence")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @Column(name = "id")
    private int id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
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

    @Column(name = "password", nullable = false)
    @ColumnTransformer(write = "md5(?)")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserLocation> locations = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Promotion> promotions = new ArrayList<>();

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

    public List<UserLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<UserLocation> locations) {
        this.locations = locations;
    }

    public void addLocation(Location location, Roles role) {
        this.locations.add(new UserLocation(this, location, role));
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
        if (this.id == 1) {
            return AuthenticationResource.ROLE_SUPERUSER;
        } else if (this.locations != null && this.promotions != null && this.locations.size() > 0 && this.promotions.size() > 0) {
            return AuthenticationResource.ROLE_OWNER_AD;
        } else if (this.locations != null && this.locations.size() > 0) {
            return AuthenticationResource.ROLE_OWNER;
        } else if (this.promotions != null && this.promotions.size() > 0) {
            return AuthenticationResource.ROLE_ADVERTISING;
        } else {
            return AuthenticationResource.ROLE_USER;
        }
    }

    public String getFullName() {
        return this.middleName != null ? this.firstName + " " + this.middleName + " " + this.lastName : this.firstName + " " + this.lastName;
    }

    public UserResponse toResponse() {
        return new UserResponse(this);
    }

    public UserResponse toResponse(boolean simple) {
        return new UserResponse(this, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(street, user.street) &&
                Objects.equals(houseNumber, user.houseNumber) &&
                Objects.equals(zipCode, user.zipCode) &&
                Objects.equals(city, user.city) &&
                Objects.equals(country, user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, email, phoneNumber, street, houseNumber, zipCode, city, country);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
