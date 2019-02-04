package nl.jtosti.hermes.company;

import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
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

    @ManyToMany(cascade = CascadeType.ALL)
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

    @OneToMany(mappedBy = "company")
    private Set<Image> images;

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
}
