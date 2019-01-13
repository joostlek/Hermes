package nl.jtosti.hermes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Location {
    @Id
    @SequenceGenerator(name = "location_generator", sequenceName = "location_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "location")
    @JsonIgnoreProperties({"location", "images"})
    private List<Screen> screens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnoreProperties({"locations", "images"})
    private User owner;

    protected Location() {
    }

    public Location(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public Location(String name, User owner, List<Screen> screens) {
        this.name = name;
        this.owner = owner;
        this.screens = screens;
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

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public void addScreen(Screen screen) {
        this.screens.add(screen);
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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
