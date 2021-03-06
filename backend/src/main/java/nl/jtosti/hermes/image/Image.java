package nl.jtosti.hermes.image;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.screen.Screen;
import nl.jtosti.hermes.user.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Image {

    @Id
    @SequenceGenerator(name = "image_generator", sequenceName = "image_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_generator")
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"images", "location"})
    private Screen screen;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnoreProperties({"images", "locations"})
    private User uploader;

    protected Image() {
    }

    public Image(String name, String url, Screen screen, User uploader) {
        this.url = url;
        this.name = name;
        this.screen = screen;
        this.uploader = uploader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        if (this.id == null) {
            return String.format("<Image: %s>", this.name);
        } else {
            return String.format("<Image %s: %s>", this.id, this.name);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Image)) {
            return false;
        }
        Image image = (Image) obj;
        return this.name.equals(image.getName()) && Objects.equals(this.id, image.getId()) && this.url.equals(image.getUrl());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
