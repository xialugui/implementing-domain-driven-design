package cn.xialugui.systeminformation.domain.model.authenticationlog.event;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.Getter;

/**
 * @author 夏露桂
 * @since 2021/11/30 20:08
 */
@Getter
public class AuthenticationFailureLogCreatedEvent extends AuthenticationLogEvent {
    private final String name;
    private final String detail;
    private final Long timestamp;
    private final String remark;

    public AuthenticationFailureLogCreatedEvent(AuthenticationLogId authenticationLogId, String name, String detail, Long timestamp, String remark) {
        super(authenticationLogId);
        this.name = name;
        this.detail = detail;
        this.timestamp = timestamp;
        this.remark = remark;
    }
}
