package cn.xialugui.systeminformation.interfaces.listener;

import cn.xialugui.sharedkernel.domain.model.event.AuthenticationFailureEvent;
import cn.xialugui.sharedkernel.domain.model.event.AuthenticationSuccessEvent;
import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import cn.xialugui.systeminformation.domain.model.authenticationlog.command.CreateAuthenticationFailureLogCommand;
import cn.xialugui.systeminformation.domain.model.authenticationlog.command.CreateAuthenticationSuccessLogCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
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
        log.debug("认证成功事件：{}", successEvent);
        commandGateway.send(new CreateAuthenticationSuccessLogCommand(
                new AuthenticationLogId(),
                successEvent.getName(),
                successEvent.getDetail(),
                successEvent.getTimestamp()
        ));
    }

    @EventHandler
    public void handle(AuthenticationFailureEvent event) {
        log.debug("认证失败事件：{}", event);
        commandGateway.send(new CreateAuthenticationFailureLogCommand(
                new AuthenticationLogId(),
                event.getName(),
                event.getDetail(),
                event.getRemark(),
                event.getTimestamp()
        ));
    }
}
