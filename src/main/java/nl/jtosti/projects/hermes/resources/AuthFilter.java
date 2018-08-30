package nl.jtosti.projects.hermes.resources;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import nl.jtosti.projects.hermes.models.Roles;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestCtx) throws IOException {
        boolean isSecure = requestCtx.getSecurityContext().isSecure();
        SecContext security = new SecContext(null, Roles.GUEST, isSecure);
        String authHeader = requestCtx.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer".length()).trim();
            try {
                JwtParser parser = Jwts.parser().setSigningKey(AuthResource.key);
                Claims claims = parser.parseClaimsJws(token).getBody();
                User user = ManagerProvider.getUserManager().get(Integer.parseInt(claims.getSubject()));
                Roles role = Roles.valueOf(claims.get("role").toString());
                security = new SecContext(user, role, isSecure);
            } catch (JwtException | IllegalArgumentException e) {
                System.out.println(e.toString());
                System.out.println("Invalid JWT, processing as guest!");
            }
        }
        requestCtx.setSecurityContext(security);
    }
}