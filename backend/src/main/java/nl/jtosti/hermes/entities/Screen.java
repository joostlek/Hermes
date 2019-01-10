package nl.jtosti.hermes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.jtosti.hermes.security.Argon2PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Screen {

    public static final PasswordEncoder PASSWORD_ENCODER = new Argon2PasswordEncoder();

    @Id
    @SequenceGenerator(name = "screen_generator", sequenceName = "screen_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "screen_generator")
    private Long id;
    private String name;
    private int width;
    private int height;
    private boolean toReceivePassword;
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"screens", "owner"})
    private Location location;

    @OneToMany(mappedBy = "screen")
    @JsonIgnoreProperties({"screen", "owner"})
    private List<Image> images = new ArrayList<>();

    protected Screen() {
    }

    public Screen(String name, int width, int height, Location location) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.location = location;
        this.toReceivePassword = true;
    }

    public Screen(String name, int width, int height, Location location, List<Image> images) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.location = location;
        this.images = images;
        this.toReceivePassword = true;
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

    void addImage(Image image) {
        this.images.add(image);
    }

    public boolean isToReceivePassword() {
        return toReceivePassword;
    }

    public void setToReceivePassword(boolean toReceivePassword) {
        this.toReceivePassword = toReceivePassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
        this.setToReceivePassword(false);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Screen)) {
            return false;
        }
        Screen screen = (Screen) obj;
        return Objects.equals(this.id, screen.getId()) && this.height == screen.getHeight() && this.width == screen.getWidth() && this.name.equals(screen.getName());
    }

    @Override
    public String toString() {
        if (this.id == null) {
            return String.format("<Screen: %s>", this.name);
        } else {
            return String.format("<Screen %s: %s>", this.id, this.name);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public UserDetails toUserDetails() {
        return new ApplicationUser(Long.toString(this.id), this.getPassword(), Collections.singletonList("SCREEN"));
    }
}
