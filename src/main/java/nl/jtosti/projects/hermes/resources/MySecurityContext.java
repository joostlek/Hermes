package nl.jtosti.projects.hermes.resources;

import nl.jtosti.projects.hermes.models.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class MySecurityContext implements SecurityContext {
    private User user;
    private String role;
    private boolean isSecure;

    public MySecurityContext(User user, String role, boolean isSecure) {
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
        if (user != null) {
            if (user.getRole().equals(AuthenticationResource.ROLE_SUPERUSER)) {
                return true;
            } else if (user.getRole().equals(role)) {
                return true;
            } else if (user.getRole().equals(AuthenticationResource.ROLE_OWNER_AD) && (role.equals(AuthenticationResource.ROLE_OWNER) || role.equals(AuthenticationResource.ROLE_ADVERTISING))) {
                return true;
            } else if (role.equals(AuthenticationResource.ROLE_USER) && !user.getRole().equals(AuthenticationResource.ROLE_GUEST)) {
                return true;
            }
        }
        return role.equals(this.role);
    }

    public boolean isSecure() {
        return isSecure;
    }

    public String getAuthenticationScheme() {
        return "Bearer";
    }
}
