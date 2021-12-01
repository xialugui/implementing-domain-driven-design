package cn.xialugui.systeminformation.query.authenticationlog;

import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationFailureLogCreatedEvent;
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationSuccessLogCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

/**
 * @author 夏露桂
 * @since 2021/11/30 20:16
 */
@Service
@RequiredArgsConstructor
public class AuthenticationLogEventHandler {

    private final AuthenticationLogViewRepository repository;

    @EventHandler
    public void on(AuthenticationSuccessLogCreatedEvent event) {
        AuthenticationLogView view = new AuthenticationLogView();

        view.setIdentifier(event.getAuthenticationLogId().getIdentifier());
        view.setName(event.getName());
        view.setType(AuthenticationType.SUCCESS);
        view.setDetail(event.getDetail());
        repository.save(view);
    }

    @EventHandler
    public void on(AuthenticationFailureLogCreatedEvent event) {
        AuthenticationLogView view = new AuthenticationLogView();
        view.setIdentifier(event.getAuthenticationLogId().getIdentifier());
        view.setName(event.getName());
        view.setRemark(event.getRemark());
        view.setType(AuthenticationType.FAILURE);
        view.setDetail(event.getDetail());
        repository.save(view);
    }
}
