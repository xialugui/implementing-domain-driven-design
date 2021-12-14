package cn.xialugui.systeminformation.domain.model.token.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * @author 夏露桂
 * @since 2021/12/8 18:38
 */
@EqualsAndHashCode(callSuper = true)
@Jacksonized
@Value
@SuperBuilder
public class TokenIssuedEvent extends TokenEvent {
    String name;
    String tokenValue;
}
