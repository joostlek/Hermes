package nl.jtosti.hermes.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EqualsAndHashCode
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "location")
    private List<Screen> screens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @Override
    public String toString() {
        return "<Location " + Long.toString(id) + ": " + name + ">";
    }
}
