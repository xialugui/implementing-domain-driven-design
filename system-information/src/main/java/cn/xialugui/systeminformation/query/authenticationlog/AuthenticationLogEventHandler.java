package cn.xialugui.systeminformation.query.authenticationlog;

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

        view.setId(event.getAuthenticationLogId().getIdentifier());
        view.setName(event.getName());
        view.setDetail(event.getDetail());
        repository.save(view);
    }
}
