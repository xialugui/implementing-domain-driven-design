package cn.xialugui.systeminformation.domain.model.authenticationlog;

import cn.xialugui.systeminformation.domain.model.authenticationlog.command.CreateAuthenticationFailureLogCommand;
import cn.xialugui.systeminformation.domain.model.authenticationlog.command.CreateAuthenticationSuccessLogCommand;
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationFailureLogCreatedEvent;
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationSuccessLogCreatedEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @author 夏露桂
 * @since 2021/11/30 17:15
 */
@Aggregate
//无参构造函数，axon框架在对象初始化时需要调用。
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthenticationLog {
    @AggregateIdentifier
    private AuthenticationLogId authenticationLogId;

    @CommandHandler
    public AuthenticationLog(CreateAuthenticationSuccessLogCommand command) {
        AggregateLifecycle.apply(new AuthenticationSuccessLogCreatedEvent(
                command.getAuthenticationLogId(),
                command.getName(),
                command.getDetail(),
                command.getTimestamp()
        ));
    }

    @CommandHandler
    public AuthenticationLog(CreateAuthenticationFailureLogCommand command) {
        AggregateLifecycle.apply(new AuthenticationFailureLogCreatedEvent(
                command.getAuthenticationLogId(),
                command.getName(),
                command.getDetail(),
                command.getTimestamp(),
                command.getRemark()
        ));
    }

    @EventSourcingHandler
    public void on(AuthenticationSuccessLogCreatedEvent event) {
        this.authenticationLogId = event.getAuthenticationLogId();
    }

    @EventSourcingHandler
    public void on(AuthenticationFailureLogCreatedEvent event) {
        this.authenticationLogId = event.getAuthenticationLogId();
    }

}