package nl.jtosti.projects.hermes.resources;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.jtosti.projects.hermes.models.Roles;
import nl.jtosti.projects.hermes.models.User;
import nl.jtosti.projects.hermes.persistence.ManagerProvider;
import nl.jtosti.projects.hermes.util.GsonProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Path("/auth")
public class AuthResource {
    public static final String ROLE_GUEST = "GUEST";
    public static final String ROLE_SUPERUSER = "SUPERUSER";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_ADVERTISER = "ADVERTISER";
    public static final String ROLE_USER = "USER";

    final static public Key key = MacProvider.generateKey();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(String body) {
        Map<String,Object> map = new HashMap<>();
        map = (Map<String,Object>) GsonProvider.getGson().fromJson(body, map.getClass());
        String email = (String) map.get("email");
        String password = (String) map.get("password");
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
            Map<String, String> res = new HashMap<>();
            res.put("token", token);
            return Response.ok(GsonProvider.getGson().toJson(res)).build();
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println(e.toString());
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
