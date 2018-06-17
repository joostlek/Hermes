package nl.jtosti.projects.hermes.models;

import nl.jtosti.projects.hermes.responses.PromotionResponse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "type", nullable = false)
    private Type type;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.PERSIST)
    private List<Image> images;

    public Promotion() {
    }

    public Promotion(String name, Date startDate, User owner, Type type) {
        this.name = name;
        this.startDate = startDate;
        this.owner = owner;
        this.type = type;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        return "<Promotion " + this.name + ">";
    }

    public PromotionResponse toResponse() {
        return new PromotionResponse(this);
    }

    public PromotionResponse toResponse(boolean simple) {
        return new PromotionResponse(this, true);
    }
}