package nl.jtosti.projects.hermes.responses;

import nl.jtosti.projects.hermes.models.Roles;
import nl.jtosti.projects.hermes.models.UserLocation;

import java.util.Date;

public class UserLocationResponse {
    private LocationResponse location;
    private UserResponse user;
    private Roles role;
    private Date createdOn;

    public UserLocationResponse() {
    }

    public UserLocationResponse(UserLocation userLocation) {
        this.location = userLocation.getLocation().toResponse(true);
        this.role = userLocation.getRole();
        this.createdOn = userLocation.getCreatedOn();
    }

    public UserLocationResponse(UserLocation userLocation, boolean user) {
        this.user = userLocation.getUser().toResponse(true);
        this.role = userLocation.getRole();
        this.createdOn = userLocation.getCreatedOn();
    }


}
