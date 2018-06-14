package nl.jtosti.projects.hermes.models;

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

    @ManyToOne
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
    }
}
