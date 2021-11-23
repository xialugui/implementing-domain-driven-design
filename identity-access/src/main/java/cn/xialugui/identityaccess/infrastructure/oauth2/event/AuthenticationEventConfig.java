package cn.xialugui.identityaccess.infrastructure.oauth2.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;

/**
 * @author 夏露桂
 * @since 2021/11/23 19:26
 */
@Configuration
public class AuthenticationEventConfig {

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher
            (ApplicationEventPublisher applicationEventPublisher) {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }
}
