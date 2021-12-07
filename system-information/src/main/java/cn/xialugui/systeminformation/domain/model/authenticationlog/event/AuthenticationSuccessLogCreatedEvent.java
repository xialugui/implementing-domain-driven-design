package cn.xialugui.systeminformation.domain.model.authenticationlog.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * @author 夏露桂
 * @since 2021/11/30 20:08
 */

@EqualsAndHashCode(callSuper = true)
@Jacksonized
@Value
@SuperBuilder
public class AuthenticationSuccessLogCreatedEvent extends AuthenticationLogEvent {
}
