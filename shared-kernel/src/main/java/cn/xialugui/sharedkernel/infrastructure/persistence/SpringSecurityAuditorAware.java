package cn.xialugui.sharedkernel.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Optional;

/**
 * Spring Security审计器，用来获取当前操作人员。
 *
 * @author 夏露桂
 * @since 2021/9/3 17:13
 */
@Slf4j
@RequiredArgsConstructor
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
            return Optional.of(token.getName());
        } else {
            log.debug("认证不是Jwt类型的：{}", authentication);
            return Optional.empty();
        }
    }
}
