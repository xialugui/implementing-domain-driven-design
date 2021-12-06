package cn.xialugui.systeminformation.domain.model.authenticationlog.command;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.Getter;

/**
 * @author 夏露桂
 * @since 2021/11/30 17:50
 */
@Getter
public class CreateAuthenticationFailureLogCommand extends AuthenticationLogCommand {

    public CreateAuthenticationFailureLogCommand(AuthenticationLogId authenticationLogId, String name, String ip, String remarks, String type, Long timestamp) {
        super(authenticationLogId, name, ip, remarks, type, timestamp);
    }
}
