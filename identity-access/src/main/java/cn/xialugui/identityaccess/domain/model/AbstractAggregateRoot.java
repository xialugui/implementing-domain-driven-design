package cn.xialugui.identityaccess.domain.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;
import org.springframework.util.Assert;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 聚合根
 *
 * @author 夏露桂
 * @since 2021/7/14 17:53
 */
@MappedSuperclass
public abstract class AbstractAggregateRoot<A extends AbstractAggregateRoot<A, ID>, ID extends Serializable> extends Entity<ID> {

    private transient final @Transient
    List<Object> domainEvents = new ArrayList<>();


    protected <T> T registerEvent(T event) {

        Assert.notNull(event, "Domain event must not be null!");

        this.domainEvents.add(event);
        return event;
    }

    @AfterDomainEventPublication
    protected void clearDomainEvents() {
        this.domainEvents.clear();
    }


    @DomainEvents
    protected Collection<Object> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    @SuppressWarnings("unchecked")
    protected final A andEventsFrom(A aggregate) {

        Assert.notNull(aggregate, "Aggregate must not be null!");

        this.domainEvents.addAll(aggregate.domainEvents());

        return (A) this;
    }

    @SuppressWarnings("unchecked")
    protected final A andEvent(Object event) {

        registerEvent(event);

        return (A) this;
    }
}
