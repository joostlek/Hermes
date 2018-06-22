package nl.jtosti.projects.hermes.models;

import nl.jtosti.projects.hermes.responses.ImageResponse;

import javax.persistence.*;

@Entity
@Table(name = "image")
@SequenceGenerator(name = "IMAGE_SEQ", sequenceName = "image_sequence")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_SEQ")
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "width", nullable = false)
    private int width;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "active")
    private boolean active;

    @Column(name = "time", nullable = false)
    private int time;

    @ManyToOne
    @JoinColumn(name = "screenId", nullable = false)
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "promotionId", nullable = false)
    private Promotion promotion;

    public Image() {
    }

    public Image(String name, int height, int width, String url, boolean active, int time, Screen screen, User owner, Promotion promotion) {
        this.name = name;
        this.height = height;
        this.width = width;
        this.url = url;
        this.active = active;
        this.time = time;
        this.screen = screen;
        this.owner = owner;
        this.promotion = promotion;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "<Image " + this.name + ">";
    }

    public ImageResponse toResponse() {
        return new ImageResponse(this);
    }

    public ImageResponse toResponse(boolean simple) {
        return new ImageResponse(this, true);
    }
}
