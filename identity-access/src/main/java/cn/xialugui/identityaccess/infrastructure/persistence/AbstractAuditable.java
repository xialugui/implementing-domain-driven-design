package cn.xialugui.identityaccess.infrastructure.persistence;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter(AccessLevel.PROTECTED)
public abstract class AbstractAuditable<U, PK extends Serializable> extends AbstractPersistable<PK> {

    private static final long serialVersionUID = 141481953116476081L;

    @ManyToOne
    @CreatedBy
    private U createdBy;

    @CreatedDate
    private Instant createdDate;

    @ManyToOne
    @LastModifiedBy
    private U lastModifiedBy;

    @LastModifiedDate
    private Instant lastModifiedDate;
}
