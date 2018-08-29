package nl.jtosti.projects.hermes.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserLocationId implements Serializable {
    @Column(name = "id")
    private int userId;

    @Column(name = "id")
    private int locationId;

    public UserLocationId() {
    }

    public UserLocationId(int userId, int locationId) {
        this.userId = userId;
        this.locationId = locationId;
    }

    public int getUserId() {
        return userId;
    }

    public int getLocationId() {
        return locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserLocationId userLocationId = (UserLocationId) o;
        return Objects.equals(userLocationId.locationId, this.locationId) && Objects.equals(userLocationId.userId, this.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, userId);
    }
}
