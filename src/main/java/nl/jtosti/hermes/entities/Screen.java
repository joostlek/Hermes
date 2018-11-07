package nl.jtosti.hermes.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Screen {
    @Id
    @SequenceGenerator(name = "screen_generator", sequenceName = "screen_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "screen_generator")
    private Long id;
    private String name;
    private int width;
    private int height;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @OneToMany(mappedBy = "screen")
    private List<Image> images = new ArrayList<>();

    public Screen() {
    }

    public Screen(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.images = new ArrayList<>();
    }

    public Screen(String name, int width, int height, Location location, List<Image> images) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.location = location;
        this.images = images;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "<Screen " + Long.toString(id) + ": " + name + ">";
    }
}
