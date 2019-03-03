package nl.jtosti.hermes.screen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.location.Location;
import nl.jtosti.hermes.security.Argon2PasswordEncoder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Screen {

    public static final PasswordEncoder PASSWORD_ENCODER = new Argon2PasswordEncoder();

    @Id
    @SequenceGenerator(name = "screen_generator", sequenceName = "screen_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "screen_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int width;

    @Column(nullable = false)
    private int height;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @Column(nullable = false)
    private boolean toReceivePassword;

    @Column
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnoreProperties({"screens", "owner"})
    private Location location;

    @OneToMany(mappedBy = "screen")
    @JsonIgnoreProperties({"screen", "owner"})
    private Set<Image> images = new HashSet<>();

    protected Screen() {
    }

    public Screen(String name, int width, int height, Location location) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.location = location;
        this.toReceivePassword = true;
    }

    public Screen(String name, int width, int height, Location location, Set<Image> images) {
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

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
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

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Stream.of("SCREEN")
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}