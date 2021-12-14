package cn.xialugui.systeminformation.query.token;

import cn.xialugui.systeminformation.domain.model.token.event.TokenIssuedEvent;
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
        view.setIdentifier(event.getTokenId().getIdentifier());
        view.setToken(event.getTokenValue());
        view.setName(event.getName());
        repository.save(view);
    }
}
