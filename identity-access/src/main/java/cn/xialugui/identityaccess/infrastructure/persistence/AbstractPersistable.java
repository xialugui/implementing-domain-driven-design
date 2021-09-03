
package cn.xialugui.identityaccess.infrastructure.persistence;

import org.springframework.data.util.ProxyUtils;
import org.springframework.lang.Nullable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractPersistable<PK extends Serializable> {

    @Id
    @GeneratedValue
    private @Nullable
    PK id;

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Persistable#getId()
     */
    @Nullable
    protected PK getId() {
        return id;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    protected void setId(@Nullable PK id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(ProxyUtils.getUserClass(obj))) {
            return false;
        }

        AbstractPersistable<?> that = (AbstractPersistable<?>) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }
}
