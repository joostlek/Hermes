package nl.jtosti.hermes.entities;

import lombok.*;
import nl.jtosti.hermes.Image;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private int width;
    private int height;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;

    @OneToMany(mappedBy = "screen")
    private List<Image> images = new ArrayList<>();

    @Override
    public String toString() {
        return "<Screen " + Long.toString(id) + ": " + name + ">";
    }
}
