package nl.jtosti.projects.hermes.models;

import javax.persistence.*;
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
}