package cn.xialugui.identityaccess.domain.model;

import cn.xialugui.identityaccess.domain.ValidationNotificationHandler;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 实体
 * 实体拥有{@code id}属性，用以区分不同对象。而值对象不存在{@code id}属性，其所有属性都是不可变的，
 * 这种不可变特性使得我们可以将其看作整形变量。
 * 实体的id是领域标识，其和数据库的主键是不一样的概念，每个实体领域标识通常是不同的。
 *
 * @author 夏露桂
 * @since 2021/7/14 18:03
 */
@Getter
@Setter
@MappedSuperclass
public abstract class Entity<ID> extends IdentifiedDomainObject<ID> {
    @Embedded
    @NaturalId
    private @Valid @NotNull ID id;

    @Override
    public ID id() {
        return id;
    }

    protected void setId(ID id) {
        this.id = id;
    }

    /**
     * 判断两个实体是否相等。
     *
     * @param object 另一被比较的对象
     * @return 实体标识相等则相同
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Entity)) {
            return false;
        }
        Entity other = (Entity) object;
        //引用相等则必相等
        if (this == other)
            return true;
        if (!this.getClass().equals(other.getClass()))
            return false;
        return this.id().equals(other.id());
    }

    public void validate(ValidationNotificationHandler validationNotificationHandler) {

    }
}
