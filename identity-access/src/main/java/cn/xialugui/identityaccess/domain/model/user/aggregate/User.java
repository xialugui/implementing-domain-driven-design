package cn.xialugui.identityaccess.domain.model.user.aggregate;

import cn.xialugui.identityaccess.domain.model.role.aggragate.Role;
import cn.xialugui.identityaccess.domain.model.user.CreateCommand;
import cn.xialugui.identityaccess.domain.model.user.valueobject.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * 用户聚合
 * <p>
 * 将实体和值对象在一致性边界之内组成聚合是DDD战术指导中最难理解的。
 * 聚合不仅是将共享父类、密切关联的对象组成对象树，而且必须遵守相应的规则。
 * 聚合模式讨论的是对象组合和信息隐藏，它还包含了一致性边界和事务。
 *
 *
 * </p>
 *
 * @author 夏露桂
 * @since 2021/7/14 17:50
 */
@Getter
@Entity
@NoArgsConstructor
public final class User extends AbstractAggregateRoot<User> {
    @EmbeddedId
    private UserId id;
    private Username username;
    private NickName nickname;
    private Email email;
    private MobilePhone mobilePhone;
    private Password password;


    /**
     * 聚合的设计应该尽可能地小，通过唯一标识符来引用其他聚合。
     * 但是在项目初期，我们可以先使用直接引用对象的方式。
     * <p>
     * {@code @ManyToMany(targetEntity = Role.class}
     * <br>
     * {@code private Set<RoleId> roleIds;}
     * </p>
     * 在多对多关系下，引用标识符无法生成中间表，所以在此处选择对象引用。
     * 另外Hibernate默认采用的是懒加载技术，虽然会有一定的性能损耗，但是
     * 其效果和标识符引用相差不大。再者此种方式对用户界面的显示也有一定的帮助。
     * 综合来讲暂时选用对象引用技术。
     */
    @ManyToMany(targetEntity = Role.class)
    private Set<Role> roles;

    public User(CreateCommand command) {
        this.password = new Password(command.getPassword());
    }
}

