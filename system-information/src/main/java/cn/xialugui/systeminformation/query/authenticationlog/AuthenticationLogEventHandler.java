package cn.xialugui.systeminformation.query.authenticationlog;

import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationFailureLogCreatedEvent;
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationLogEvent;
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationSuccessLogCreatedEvent;
import cn.xialugui.systeminformation.domain.model.authenticationlog.valueobject.Detail;
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
        repository.save(build(event, AuthenticationType.SUCCESS));
    }

    @EventHandler
    public void on(AuthenticationFailureLogCreatedEvent event) {
        repository.save(build(event, AuthenticationType.FAILURE));
    }

    private AuthenticationLogView build(AuthenticationLogEvent event, AuthenticationType authenticationType) {
        AuthenticationLogView view = new AuthenticationLogView();
        view.setIdentifier(event.getAuthenticationLogId().getIdentifier());
        view.setName(event.getName());
        view.setType(authenticationType);
        view.setIp(event.getIp());
        view.setDetailType(Detail.Type.ofClassName(event.getType()));
        view.setRemarks(event.getRemarks());
        view.setTimestamp(event.getTimestamp());
        return view;
    }


}
