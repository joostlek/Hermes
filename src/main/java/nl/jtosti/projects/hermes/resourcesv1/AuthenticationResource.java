package nl.jtosti.projects.hermes.resourcesv1;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Calendar;

@Path("/authentication")
public class AuthenticationResource {
    public static final String ROLE_OWNER_AD = "OwnerAd";
    public static final String ROLE_SUPERUSER = "Superuser";
    public static final String ROLE_ADVERTISING = "Advertiser";
    public static final String ROLE_OWNER = "Owner";
    public static final String ROLE_USER = "User";
    public static final String ROLE_GUEST = "Guest";

    final static public Key key = MacProvider.generateKey();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("email") String email,
                                     @FormParam("password") String password) {
        try {
            User user = ManagerProvider.getUserManager().getUserByLogin(email, password);
            if (user == null) {
                throw new IllegalArgumentException("No user found!");
            }
            Calendar expiration = Calendar.getInstance();
            expiration.add(Calendar.MINUTE, 30);
            String token = Jwts.builder()
                    .setSubject(Integer.toString(user.getId()))
                    .claim("role", user.getRole())
                    .setExpiration(expiration.getTime())
                    .signWith(SignatureAlgorithm.HS512, key)
                    .compact();
            return Response.ok(GsonProvider.getGson().toJson(token)).build();
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println(e.toString());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
