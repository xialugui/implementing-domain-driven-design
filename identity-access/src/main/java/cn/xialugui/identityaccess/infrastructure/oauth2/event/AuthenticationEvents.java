package cn.xialugui.identityaccess.infrastructure.oauth2.event;

import cn.xialugui.sharedkernel.domain.model.event.AuthenticationFailureEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationEvents {
    private final EventGateway eventGateway;

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        Authentication authentication = success.getAuthentication();
        log.debug("认证成功：{}，类型：{}", authentication.getName(), authentication.getClass().getName());
        String detail = authentication.getDetails() == null ? null : authentication.getDetails().toString();
        if (!(authentication instanceof JwtAuthenticationToken)) {
            eventGateway.publish(
                    cn.xialugui.sharedkernel.domain.model.event.AuthenticationSuccessEvent.builder()
                            .name(authentication.getName())
                            .ip(detail)
                            .name(authentication.getClass().getName())
                            .timestamp(success.getTimestamp())
                            .build());
        } else {
            log.debug("Jwt 认证成功：{}", authentication.getName());
        }
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        Authentication authentication = failures.getAuthentication();
        log.debug("认证失败：{}，类型：{}", authentication.getName(), authentication.getClass().getName());
        String detail = authentication.getDetails() == null ? null : authentication.getDetails().toString();
        eventGateway.publish(
                AuthenticationFailureEvent.builder()
                        .name(authentication.getName())
                        .ip(detail)
                        .remarks(failures.getException().getMessage())
                        .type(authentication.getClass().getName())
                        .timestamp(failures.getTimestamp())
        );
    }
}