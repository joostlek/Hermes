package nl.jtosti.hermes.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
@EqualsAndHashCode
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "location")
    private List<Screen> screens = new ArrayList<>();

    @Column(name = "owner")
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
}
