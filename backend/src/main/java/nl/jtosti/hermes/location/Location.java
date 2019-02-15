package nl.jtosti.hermes.location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.screen.Screen;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.*;

@Entity
public class Location {
    @Id
    @SequenceGenerator(name = "location_generator", sequenceName = "location_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

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

    @OneToMany(mappedBy = "location")
    @JsonIgnoreProperties({"location", "images"})
    private List<Screen> screens;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnoreProperties({"locations", "images"})
    private Company company;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(
            name = "company_locations",
            joinColumns = {
                    @JoinColumn(name = "location_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "company_id")
            }
    )
    private Set<Company> advertisingCompanies;

    protected Location() {
        this.screens = new ArrayList<>();
        this.advertisingCompanies = new HashSet<>();
    }

    public Location(String name, String street, String houseNumber, String zipCode, String city, String country, Company company) {
        this();
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
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

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public void addScreen(Screen screen) {
        this.screens.add(screen);
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Company> getAdvertisingCompanies() {
        return advertisingCompanies;
    }

    public void setAdvertisingCompanies(Set<Company> advertisingCompanies) {
        this.advertisingCompanies = advertisingCompanies;
    }

    public void addAdvertisingCompanies(Company company) {
        this.advertisingCompanies.add(company);
    }

    public boolean hasAdvertisingCompany(Company company) {
        for (Company comp : this.advertisingCompanies) {
            if (comp.equals(company)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (this.id == null) {
            return String.format("<Location: %s>", name);
        } else {
            return String.format("<Location %s: %s>", id, name);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Location)) {
            return false;
        }
        Location location = (Location) obj;
        return Objects.equals(this.id, location.getId()) && this.name.equals(location.getName());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
