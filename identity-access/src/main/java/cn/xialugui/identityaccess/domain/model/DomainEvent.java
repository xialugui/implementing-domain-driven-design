package cn.xialugui.identityaccess.domain.model;

import java.time.LocalDateTime;

/**
 * 领域事件接口
 *
 * @author 夏露桂
 * @since 2021/8/17 10:57
 */
public interface DomainEvent {
    LocalDateTime occurredOn();
}
