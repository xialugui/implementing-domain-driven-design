package cn.xialugui.identityaccess.domain.model;

import java.time.LocalDateTime;

/**
 * @author 夏露桂
 * @since 2021/8/17 11:09
 */
public class AbstractDomainEvent implements DomainEvent {
    private LocalDateTime occurredOn;

    public AbstractDomainEvent() {
        setOccurredOn(LocalDateTime.now());
    }

    private void setOccurredOn(LocalDateTime occurredOn) {
        this.occurredOn = occurredOn;
    }

    @Override
    public LocalDateTime occurredOn() {
        return this.occurredOn;
    }
}
