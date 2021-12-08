package cn.xialugui.sharedkernel.domain.model.event;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

/**
 * @author 夏露桂
 * @since 2021/12/8 18:19
 */
@Value
@Builder
@Jacksonized
public class AccessTokenIssuedEvent {
    String accessTokenValue;
    Instant accessTokenIssuedAt;
    Instant accessTokenExpiresAt;
    String accessTokenType;


    String refreshTokenValue;
    Instant refreshTokenIssuedAt;
    Instant refreshTokenExpiresAt;

    String name;
    String detail;
    String clientId;
    String clientName;
}
