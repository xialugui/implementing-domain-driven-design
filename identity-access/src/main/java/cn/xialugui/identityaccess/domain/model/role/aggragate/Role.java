package cn.xialugui.identityaccess.domain.model.role.aggragate;

import cn.xialugui.identityaccess.domain.model.HibernateAggregateRoot;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleName;
import cn.xialugui.identityaccess.domain.model.user.valueobject.Username;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 角色聚合
 *
 * @author 夏露桂
 * @since 2021/7/14 19:38
 */
@Getter
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class Role extends HibernateAggregateRoot<Role> {

    /**
     * {@code @NaturalId}是{@code Hibernate}的注解，保证其表达不可变性，相当于{@link cn.xialugui.identityaccess.domain.model.user.aggregate.User#setUsername(Username)}
     * 方法。详情参考<a href="https://docs.jboss.org/hibernate/orm/5.5/userguide/html_single/Hibernate_User_Guide.html#naturalid"/>Natural Ids</a>
     **/
    @Embedded
    @NaturalId
    private @Valid @NotNull RoleId roleId;

    @Embedded
    private @Valid @NotNull RoleName name;

    public void changeName(RoleName roleName) {
        setName(roleName);
    }
}
