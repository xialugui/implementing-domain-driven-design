package cn.xialugui.systeminformation.domain.model.authenticationlog.event;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author 夏露桂
 * @since 2021/11/30 20:08
 */
@SuperBuilder
@Getter
@RequiredArgsConstructor
public class AuthenticationLogEvent {
    private final AuthenticationLogId authenticationLogId;
    private final String name;
    private final String ip;
    private final String remarks;
    private final String type;
    private final Long timestamp;
}
