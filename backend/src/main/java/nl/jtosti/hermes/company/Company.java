package nl.jtosti.hermes.company;

import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Company {
    @Id
    @SequenceGenerator(name = "company_generator", sequenceName = "company_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_generator")
    private Long id;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String houseNumber;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "company_users",
            joinColumns = {
                    @JoinColumn(name = "company_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    private Set<User> users;

    @OneToMany(mappedBy = "company")
    private Set<Location> locations;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "company_locations",
            joinColumns = {
                    @JoinColumn(name = "company_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "location_id")
            }
    )
    private Set<Location> advertisingLocations;

    @OneToMany(mappedBy = "company")
    private Set<Image> images;

    public Company() {
        this.users = new HashSet<>();
        this.locations = new HashSet<>();
        this.images = new HashSet<>();
        this.advertisingLocations = new HashSet<>();
    }

    public Company(String phoneNumber, String name, String street, String houseNumber, String zipCode, String city, String country) {
        this();
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public boolean hasUser(User user) {
        if (this.users == null || this.users.isEmpty()) {
            return false;
        }
        for (User user1 : this.users) {
            if (user.equals(user1)) {
                return true;
            }
        }
        return false;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Image> getImagesByLocation(Location location) {
        Set<Image> locationImages = new HashSet<>();
        for (Image image : this.images) {
            if (location.getScreens().contains(image.getScreen())) {
                locationImages.add(image);
            }
        }
        return locationImages;
    }

    public Set<Location> getAdvertisingLocations() {
        return advertisingLocations;
    }

    public void setAdvertisingLocations(Set<Location> advertisingLocations) {
        this.advertisingLocations = advertisingLocations;
    }

    public void addAdvertisingLocation(Location location) {
        this.advertisingLocations.add(location);
    }
}
