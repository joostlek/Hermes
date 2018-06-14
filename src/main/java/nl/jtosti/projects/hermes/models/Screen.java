package nl.jtosti.projects.hermes.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "screen")
public class Screen {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

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
}
