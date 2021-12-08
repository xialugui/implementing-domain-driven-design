package cn.xialugui.sharedkernel.infrastructure.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author 夏露桂
 * @since 2021/12/8 14:11
 */
@MappedSuperclass
public abstract class PropertiesIgnoredPersistable<PK extends Serializable> extends AbstractPersistable<PK> {
    @Override
    @JsonIgnore
    public PK getId() {
        return super.getId();
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return super.isNew();
    }
}
