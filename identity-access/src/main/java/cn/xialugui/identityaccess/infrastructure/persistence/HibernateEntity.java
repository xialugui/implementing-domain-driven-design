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
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class HibernateEntity<NID extends Serializable>
        extends AbstractAuditable<User, Long>
        implements Identifiable<NID> {

    @Embedded
    @NaturalId
    private @Valid @NotNull NID naturalId;

    @Override
    public NID naturalId() {
        return naturalId;
    }

    protected void setNaturalId(NID naturalId) {
        this.naturalId = naturalId;
    }
}
