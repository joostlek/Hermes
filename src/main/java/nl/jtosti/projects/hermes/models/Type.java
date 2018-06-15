package nl.jtosti.projects.hermes.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "time", nullable = false)
    private int time;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "exclusive", nullable = false)
    private boolean exclusive;

    @Column(name = "amountofimages", nullable = false)
    private int amountOfImages;

    @ManyToOne
    @JoinColumn(name = "location", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    private List<Promotion> promotions;

    public Type() {
    }

    public Type(String name, int time, int price, boolean active, boolean exclusive, int amountOfImages, Location location) {
        this.name = name;
        this.time = time;
        this.price = price;
        this.active = active;
        this.exclusive = exclusive;
        this.amountOfImages = amountOfImages;
        this.location = location;
        this.promotions = new ArrayList<>();
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public int getAmountOfImages() {
        return amountOfImages;
    }

    public void setAmountOfImages(int amountOfImages) {
        this.amountOfImages = amountOfImages;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    @Override
    public String toString() {
        return "<Type " + this.name + ">";
    }
}
