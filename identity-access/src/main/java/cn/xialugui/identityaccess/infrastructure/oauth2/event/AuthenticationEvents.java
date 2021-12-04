package cn.xialugui.identityaccess.infrastructure.oauth2.event;

import cn.xialugui.sharedkernel.domain.model.event.AuthenticationFailureEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationEvents {
    private final EventGateway eventGateway;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        Authentication authentication = success.getAuthentication();
        log.debug("认证成功：{}", authentication.getName());
        String detail = authentication.getDetails() == null ? null : authentication.getDetails().toString();
        eventGateway.publish(
                new cn.xialugui.sharedkernel.domain.model.event.AuthenticationSuccessEvent(
                        authentication.getName(),
                        detail,
                        success.getTimestamp()
                )
        );

    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        Authentication authentication = failures.getAuthentication();
        log.debug("认证失败：{}", authentication.getName());
        String detail = authentication.getDetails() == null ? null : authentication.getDetails().toString();
        eventGateway.publish(
                new AuthenticationFailureEvent(
                        authentication.getName(),
                        detail,
                        failures.getException().getMessage(),
                        failures.getTimestamp()
                )
        );
    }
}