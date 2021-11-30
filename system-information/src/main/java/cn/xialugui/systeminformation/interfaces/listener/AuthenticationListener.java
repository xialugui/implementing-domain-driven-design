package cn.xialugui.systeminformation.interfaces.listener;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import cn.xialugui.systeminformation.domain.model.authenticationlog.command.CreateAuthenticationSuccessLogCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @author 夏露桂
 * @since 2021/11/30 19:13
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationListener {

    private final CommandGateway commandGateway;

    @EventHandler
    public void handle(AuthenticationSuccessEvent successEvent) {
        commandGateway.send(new CreateAuthenticationSuccessLogCommand(
                new AuthenticationLogId(),
                successEvent.getAuthentication().getName(),
                successEvent.getAuthentication().getDetails().toString(),
                successEvent.getTimestamp()
        ));
    }
}
