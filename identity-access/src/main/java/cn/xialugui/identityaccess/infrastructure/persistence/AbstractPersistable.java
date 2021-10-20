
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
    private PK pk;

    protected PK getPk() {
        return pk;
    }

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    protected void setPk(@Nullable PK id) {
        this.pk = id;
    }


    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getPk());
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

        return null == this.getPk() ? false : this.getPk().equals(that.getPk());
    }

    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getPk() ? 0 : getPk().hashCode() * 31;

        return hashCode;
    }
}
