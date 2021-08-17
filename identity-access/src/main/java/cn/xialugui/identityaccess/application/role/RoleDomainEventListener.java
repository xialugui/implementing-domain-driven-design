package cn.xialugui.identityaccess.application.role;

import cn.xialugui.identityaccess.domain.model.user.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 领域事件接收方
 *
 * @author 夏露桂
 * @since 2021/8/17 15:55
 */
@Component
@Slf4j
public class RoleDomainEventListener {
    @TransactionalEventListener
    public void handleEvent(UserCreatedEvent event) {
        log.info("用户：{}，id：{}，于：{}创建。", event.username(), event.userId(), event.occurredOn());
    }
}
