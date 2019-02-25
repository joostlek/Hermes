package nl.jtosti.hermes.security;

import nl.jtosti.hermes.security.screen.ScreenAuthenticationProvider;
import nl.jtosti.hermes.security.user.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.lang.Integer;


import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableRedisHttpSession
@PropertySource("application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final ScreenAuthenticationProvider screenAuthenticationProvider;
    
    @Value("${spring.redis.host}")
    private String hostName;
    
    @Value("${spring.redis.password}")
    private String password;
    
    @Value("${spring.redis.port}")
    private String port;
      



    @Autowired
    public SecurityConfig(UserAuthenticationProvider userAuthenticationProvider, ScreenAuthenticationProvider screenAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.screenAuthenticationProvider = screenAuthenticationProvider;
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration rsc = new RedisStandaloneConfiguration(hostName, Integer.parseInt(port));
        rsc.setPassword(RedisPassword.of(password));
        LettuceConnectionFactory rcf = new
LettuceConnectionFactory(rsc);
        return rcf;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.toString()))
                .and()
                .httpBasic()
                .and()
                .logout()
                .defaultLogoutSuccessHandlerFor(new HttpStatusReturningLogoutSuccessHandler(), new AntPathRequestMatcher("/logout"))
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/screen/register").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider)
                .authenticationProvider(screenAuthenticationProvider);
    }
}
