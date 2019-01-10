package nl.jtosti.hermes.security.jwt;

import java.util.List;

public class JwtTokenFactory {
    private List<String> roles;
    private String username;
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFactory(String username, List<String> roles, JwtTokenProvider jwtTokenProvider) {
        this.roles = roles;
        this.username = username;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String getToken() {
        return jwtTokenProvider.createToken(username, roles);
    }
}
