package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.Roles;
import nl.jtosti.projects.hermes.models.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class SecContext implements SecurityContext {
    private User user;
    private Roles role;
    private boolean isSecure;

    public SecContext(User user, Roles role, boolean isSecure) {
        this.user = user;
        this.role = role;
    }

    public Principal getUserPrincipal() {
        return new Principal() {
            @Override
            public String getName() {
                return Integer.toString(user.getId());
            }
        };
    }

    public boolean isUserInRole(String role) {
        if (role.equals(Roles.USER.toString()) && user != null) {
            return true;
        }
        return role.equals(this.role.toString());
    }

    public boolean isSecure() {
        return isSecure;
    }

    public String getAuthenticationScheme() {
        return "Bearer";
    }
}
