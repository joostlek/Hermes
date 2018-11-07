package nl.jtosti.hermes.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@EqualsAndHashCode
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Screen screen;

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
}
