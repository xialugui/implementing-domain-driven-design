package cn.xialugui.identityaccess.infrastructure.oauth2.event;

import cn.xialugui.sharedkernel.domain.model.event.AuthenticationFailureEvent;
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
        log.debug("认证成功：{}", success.getAuthentication().getName());
        eventGateway.publish(
                new cn.xialugui.sharedkernel.domain.model.event.AuthenticationSuccessEvent(
                        success.getAuthentication().getName(),
                        success.getAuthentication().getDetails().toString(),
                        success.getTimestamp()
                )
        );

    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        log.debug("认证失败：{}", failures.getAuthentication().getName());
        eventGateway.publish(
                new AuthenticationFailureEvent(
                        failures.getAuthentication().getName(),
                        failures.getAuthentication().getDetails().toString(),
                        failures.getException().getMessage(),
                        failures.getTimestamp()
                )
        );
    }
}