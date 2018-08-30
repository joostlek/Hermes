package nl.jtosti.projects.hermes.models;

import nl.jtosti.projects.hermes.responses.UserLocationResponse;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "UserLocation")
@Table(name = "user_location")
public class UserLocation {
    @EmbeddedId
    private UserLocationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("locationId")
    private Location location;

    @Column(name = "created_on")
    private Date createdOn = new Date();

    @Column(name = "role")
    private Roles role;

    public UserLocation() {
    }

    public UserLocation(User user, Location location, Roles role) {
        this.user = user;
        this.location = location;
        this.role = role;
        this.id = new UserLocationId(user.getId(), location.getId());
    }

    public UserLocationId getId() {
        return id;
    }

    public void setId(UserLocationId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLocation that = (UserLocation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(location, that.location) &&
                Objects.equals(createdOn, that.createdOn) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, location, createdOn, role);
    }

    public UserLocationResponse toResponse(boolean user) {
        return new UserLocationResponse(this, true);
    }

    public UserLocationResponse toResponse() {
        return new UserLocationResponse(this);
    }
}
