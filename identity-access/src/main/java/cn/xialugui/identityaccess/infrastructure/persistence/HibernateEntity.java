package cn.xialugui.identityaccess.infrastructure.persistence;

import cn.xialugui.identityaccess.domain.model.Identifiable;
import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import org.hibernate.annotations.NaturalId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Embedded;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class HibernateEntity<I> extends AbstractAuditable<User, Long> implements Identifiable<I> {

    @Embedded
    @NaturalId
    private @Valid @NotNull I id;

    @Override
    public I id() {
        return id;
    }

    protected void setId(I identifier) {
        this.id = identifier;
    }
}
