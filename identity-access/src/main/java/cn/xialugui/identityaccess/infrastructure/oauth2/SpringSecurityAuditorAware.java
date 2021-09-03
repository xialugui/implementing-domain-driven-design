package cn.xialugui.identityaccess.infrastructure.oauth2;

import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.authentication.OAuth2AuthenticationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Spring Security审计器，用来获取当前操作人员。
 *
 * @author 夏露桂
 * @since 2021/9/3 17:13
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(User.class::cast);
    }
}
