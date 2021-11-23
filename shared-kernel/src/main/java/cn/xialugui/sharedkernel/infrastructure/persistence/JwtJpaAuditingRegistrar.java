package cn.xialugui.sharedkernel.infrastructure.persistence;

import org.springframework.context.annotation.Bean;

/**
 * @author 夏露桂
 * @since 2021/11/23 18:42
 */
public class JwtJpaAuditingRegistrar {

    @Bean
    SpringSecurityAuditorAware springSecurityAuditorAware() {
        return new SpringSecurityAuditorAware();
    }
}
