package cn.xialugui.systeminformation.domain.model.authenticationlog.command;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.Getter;

/**
 * @author 夏露桂
 * @since 2021/11/30 17:50
 */
@Getter
public class CreateAuthenticationSuccessLogCommand extends AuthenticationLogCommand {
    private final String name;
    private final String detail;
    private final Long timestamp;

    public CreateAuthenticationSuccessLogCommand(AuthenticationLogId authenticationLogId,
                                                 String name,
                                                 String detail,
                                                 long timestamp) {
        super(authenticationLogId);
        this.name = name;
        this.detail = detail;
        this.timestamp = timestamp;
    }
}
