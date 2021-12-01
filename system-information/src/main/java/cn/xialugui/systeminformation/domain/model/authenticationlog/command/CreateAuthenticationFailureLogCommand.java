package cn.xialugui.systeminformation.domain.model.authenticationlog.command;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.Getter;

/**
 * @author 夏露桂
 * @since 2021/11/30 17:50
 */
@Getter
public class CreateAuthenticationFailureLogCommand extends AuthenticationLogCommand {
    private final String name;
    private final String detail;
    private final String remark;
    private final Long timestamp;

    public CreateAuthenticationFailureLogCommand(AuthenticationLogId authenticationLogId,
                                                 String name,
                                                 String detail,
                                                 String remark, long timestamp) {
        super(authenticationLogId);
        this.name = name;
        this.detail = detail;
        this.remark = remark;
        this.timestamp = timestamp;
    }
}
