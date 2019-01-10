package nl.jtosti.hermes.security.jwt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtTokenFactory {
    private List<String> roles;
    private String username;
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFactory(List<String> roles, String username, JwtTokenProvider jwtTokenProvider) {
        this.roles = roles;
        this.username = username;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Map<Object, Object> getResponse() {
        String token = jwtTokenProvider.createToken(username, roles);
        Map<Object, Object> model = new HashMap<>();
        model.put("username", username);
        model.put("token", token);
        return model;
    }
}
