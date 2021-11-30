package cn.xialugui.identityaccess.infrastructure.oauth2.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationEvents {
    private final EventGateway eventGateway;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        eventGateway.publish(success);
        log.debug("认证成功：{}", success);
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        eventGateway.publish(failures);
        log.debug("认证失败：{}", failures);
    }
}