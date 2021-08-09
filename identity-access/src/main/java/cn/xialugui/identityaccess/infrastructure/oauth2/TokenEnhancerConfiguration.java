package cn.xialugui.identityaccess.infrastructure.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * @author 夏露桂
 * @since 2021/4/2 16:20
 */
@Configuration
public class TokenEnhancerConfiguration {
    @Bean
    public TokenEnhancer userIdTokenEnhancer() {
        return new UserIdTokenEnhancer();
    }
}
