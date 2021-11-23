package cn.xialugui.sharedkernel.infrastructure.persistence;

import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.lang.annotation.*;

/**
 * @author 夏露桂
 * @since 2021/11/23 18:41
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableJpaAuditing
@Import(JwtJpaAuditingRegistrar.class)
public @interface EnableJwtJpaAuditing {
}
