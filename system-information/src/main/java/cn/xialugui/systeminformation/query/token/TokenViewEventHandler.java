package cn.xialugui.systeminformation.query.token;

import cn.xialugui.sharedkernel.domain.model.event.TokenRevocatedEvent;
import cn.xialugui.systeminformation.domain.model.token.event.TokenIssuedEvent;
import cn.xialugui.systeminformation.domain.model.token.valueobject.TokenStatus;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

/**
 * @author 夏露桂
 * @since 2021/11/30 20:16
 */
@Service
@RequiredArgsConstructor
public class TokenViewEventHandler {

    private final TokenViewRepository repository;

    @EventHandler
    public void on(TokenIssuedEvent event) {
        TokenView view = new TokenView();
        view.setTokenId(event.getTokenId());
        view.setValue(event.getValue());
        view.setName(event.getName());
        view.setStatus(TokenStatus.NORMAL.name());
        repository.save(view);
    }

    @EventHandler
    public void on(TokenRevocatedEvent event) {
        TokenView view = repository.findByTokenId(event.getTokenId());
        view.setStatus(TokenStatus.REVOCATED.name());
        repository.save(view);
    }
}
