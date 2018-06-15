package nl.jtosti.projects.hermes.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "screen")
public class Screen {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "width", nullable = false)
    private int width;

    @Column(name = "allowads", nullable = false)
    private boolean allowAds;

    @ManyToOne
    @JoinColumn(name = "locationId", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.PERSIST)
    private List<Image> images;

    public Screen() {
    }

    public Screen(String name, int height, int width, boolean allowAds, Location location) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.allowAds = allowAds;
        this.location = location;
        this.images = new ArrayList<>();
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isAllowAds() {
        return allowAds;
    }

    public void setAllowAds(boolean allowAds) {
        this.allowAds = allowAds;
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

    public void addImage(Image image) {
        this.images.add(image);
    }

    @Override
    public String toString() {
        return "<Screen " + this.name + ">";
    }
}
