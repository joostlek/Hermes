package nl.jtosti.hermes.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {
    @Id
    @SequenceGenerator(name = "location_generator", sequenceName = "location_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "location")
    private List<Screen> screens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    public Location() {
    }

    public Location(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.screens = new ArrayList<>();
    }

    public Location(String name, List<Screen> screens, User owner) {
        this.name = name;
        this.screens = screens;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "<Location " + Long.toString(id) + ": " + name + ">";
    }
}
