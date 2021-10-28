package cn.xialugui.identityaccess.infrastructure.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DefaultSecurityConfig {
    private static final String[] EXCLUDE_URLS = {
            "/h2-console/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/index.html",
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin()
                .and()
                .cors().disable()
                .csrf().disable()
                .authorizeRequests(authorizeRequestsCustomizer ->
                        authorizeRequestsCustomizer
                                .antMatchers(EXCLUDE_URLS).permitAll()
                                .antMatchers(HttpMethod.POST, "/users").permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .formLogin()
        ;

        return http.build();
    }

}