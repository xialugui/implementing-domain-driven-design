package cn.xialugui.systeminformation.domain.model.authenticationlog.command;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author 夏露桂
 * @since 2021/11/30 17:50
 */
@Getter
@RequiredArgsConstructor
public abstract class AuthenticationLogCommand {

    @TargetAggregateIdentifier
    private final AuthenticationLogId authenticationLogId;
    private final String name;
    private final String ip;

    private final String remarks;
    private final String type;

    private final Long timestamp;


}
