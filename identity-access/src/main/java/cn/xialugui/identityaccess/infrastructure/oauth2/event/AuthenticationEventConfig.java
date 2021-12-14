package cn.xialugui.identityaccess.infrastructure.oauth2.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 夏露桂
 * @since 2021/11/23 19:26
 */
@Configuration
public class AuthenticationEventConfig {

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher
            (ApplicationEventPublisher applicationEventPublisher) {
        DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher = new DefaultAuthenticationEventPublisher(applicationEventPublisher);
        Map<Class<? extends AuthenticationException>, Class<? extends AbstractAuthenticationFailureEvent>> mappings = new HashMap<>() {{
            put(OAuth2AuthenticationException.class, AuthenticationFailureBadCredentialsEvent.class);
        }};

        defaultAuthenticationEventPublisher.setAdditionalExceptionMappings(mappings);
        return defaultAuthenticationEventPublisher;
    }
}
