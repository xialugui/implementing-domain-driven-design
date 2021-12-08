package cn.xialugui.identityaccess.infrastructure.oauth2.event;

import cn.xialugui.sharedkernel.domain.model.event.AccessTokenIssuedEvent;
import cn.xialugui.sharedkernel.domain.model.event.AuthenticationFailureEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.time.Instant;

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

        if (authentication instanceof JwtAuthenticationToken) {
            log.debug("Jwt 认证成功：{}", authentication.getName());
        } else {
            eventGateway.publish(
                    cn.xialugui.sharedkernel.domain.model.event.AuthenticationSuccessEvent.builder()
                            .name(authentication.getName())
                            .ip(detail)
                            .type(authentication.getClass().getName())
                            .timestamp(success.getTimestamp())
                            .build());
            if (authentication instanceof OAuth2AccessTokenAuthenticationToken) {
                publishAccessTokenIssuedEvent(authentication);
            }
        }
    }

    private void publishAccessTokenIssuedEvent(Authentication authentication) {
        OAuth2AccessTokenAuthenticationToken token = (OAuth2AccessTokenAuthenticationToken) authentication;
        OAuth2AccessToken accessToken = token.getAccessToken();
        OAuth2RefreshToken refreshToken = token.getRefreshToken();
        RegisteredClient registeredClient = token.getRegisteredClient();

        String refreshTokenValue = null;
        Instant refreshTokenIssuedAt = null;
        Instant refreshTokenExpiresAt = null;
        if (null != refreshToken) {
            refreshTokenValue = refreshToken.getTokenValue();
            refreshTokenIssuedAt = refreshToken.getIssuedAt();
            refreshTokenExpiresAt = refreshToken.getExpiresAt();
        }
        String detail = token.getDetails() == null ? null : token.getDetails().toString();

        eventGateway.publish(
                AccessTokenIssuedEvent.builder()
                        .accessTokenValue(accessToken.getTokenValue())
                        .accessTokenIssuedAt(accessToken.getIssuedAt())
                        .accessTokenExpiresAt(accessToken.getExpiresAt())
                        .accessTokenType(accessToken.getTokenValue())
                        .refreshTokenValue(refreshTokenValue)
                        .refreshTokenIssuedAt(refreshTokenIssuedAt)
                        .refreshTokenExpiresAt(refreshTokenExpiresAt)
                        .name(token.getName())
                        .clientId(registeredClient.getClientId())
                        .clientName(registeredClient.getClientName())
                        .detail(detail)
                        .build()
        );
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
                        .build()
        );
    }
}