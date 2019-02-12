package nl.jtosti.hermes.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.jtosti.hermes.screen.auth.ScreenLoginService;
import nl.jtosti.hermes.user.auth.UserLoginService;
import nl.jtosti.hermes.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    private final ScreenLoginService screenLoginService;

    private Key key = MacProvider.generateKey();
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h
    @Value("${security.jwt.token.refresh-expire-length:604800000}")
    private long refreshValidityInMilliseconds = 604800000; // 1w

    @Autowired
    public JwtTokenProvider(UserLoginService userDetailsService, ScreenLoginService screenLoginService) {
        this.userDetailsService = userDetailsService;
        this.screenLoginService = screenLoginService;
    }

    public String createToken(String username, List<String> roles) {
        Date now = new Date();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);
        claims.put("uxp", new Date(now.getTime() + validityInMilliseconds).getTime() / 1000);
        Date validity = new Date(now.getTime() + refreshValidityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails;
        try {
            userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        } catch (UserNotFoundException ex) {
            userDetails = this.screenLoginService.loadUserByUsername(getUsername(token));
        }
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            long expireDate = Integer.toUnsignedLong((int) claims.getBody().get("uxp")) * 1000;
            return new Date(expireDate).after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return claims.getBody().getExpiration().after(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }

    }
}
