package nl.jtosti.hermes.Entities;

import lombok.*;

import javax.persistence.*;

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
    private String naam;
    private int width;
    private int height;

    @ManyToOne(fetch = FetchType.LAZY)
    private Location location;
}
