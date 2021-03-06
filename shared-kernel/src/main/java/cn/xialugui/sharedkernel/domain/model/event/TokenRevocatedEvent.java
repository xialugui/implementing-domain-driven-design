package cn.xialugui.sharedkernel.domain.model.event;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * @author 夏露桂
 * @since 2021/12/8 18:19
 */
@Value
@Builder
@Jacksonized
public class TokenRevocatedEvent {
    Long tokenId;
    String value;
    String name;
    String detail;
}
