package nl.jtosti.hermes.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "owner")
    private List<Location> locations = new ArrayList<>();
    @OneToMany(mappedBy = "owner")
    private List<Image> images = new ArrayList<>();

    protected User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "<User " + Long.toString(id) + ": " + firstName + " " + lastName + ">";
    }
}
