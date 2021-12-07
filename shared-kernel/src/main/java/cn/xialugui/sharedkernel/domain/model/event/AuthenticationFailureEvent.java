package cn.xialugui.sharedkernel.domain.model.event;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * @author 夏露桂
 * @since 2021/12/1 16:31
 */
@Value
@Jacksonized
@Builder
public class AuthenticationFailureEvent {
    String name;
    String ip;
    String remarks;
    String type;
    Long timestamp;
}
