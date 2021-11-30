package cn.xialugui.systeminformation.domain.model.authenticationlog.command;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author 夏露桂
 * @since 2021/11/30 17:50
 */
@Getter
public abstract class AuthenticationLogCommand {

    @TargetAggregateIdentifier
    private final AuthenticationLogId authenticationLogId;

    public AuthenticationLogCommand(AuthenticationLogId authenticationLogId) {
        this.authenticationLogId = authenticationLogId;
    }
}
