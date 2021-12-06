package cn.xialugui.systeminformation.domain.model.authenticationlog.event;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.Getter;

/**
 * @author 夏露桂
 * @since 2021/11/30 20:08
 */
@Getter
public class AuthenticationFailureLogCreatedEvent extends AuthenticationLogEvent {

    public AuthenticationFailureLogCreatedEvent(AuthenticationLogId authenticationLogId, String name, String ip, String remarks, String type, Long timestamp) {
        super(authenticationLogId, name, ip, remarks, type, timestamp);
    }
}
