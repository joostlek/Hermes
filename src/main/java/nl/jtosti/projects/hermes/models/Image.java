package nl.jtosti.projects.hermes.models;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "width", nullable = false)
    private int width;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "active", nullable = false)
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
}
