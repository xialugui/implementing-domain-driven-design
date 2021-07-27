package cn.xialugui.identityaccess.domain.model.user.aggregate;

import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import cn.xialugui.identityaccess.domain.model.user.CreateCommand;
import cn.xialugui.identityaccess.domain.model.user.valueobject.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
     * {@code private Set<Role> Role;}
     * </p>
     * 对象引用带来的问题是每次加载的内存消耗，如果集合数量庞大，那么
     * 代价是非常巨大的。虽然Hibernate拥有懒加载机制，但我们仍不建议使用。
     * <p>
     * 当然，对象引用的好处是方便UI层显示，但有时坚持原则的好处更大。
     *
     */
    @ElementCollection
    private Set<RoleId> roleIds;

    public User(CreateCommand command) {
        this.password = new Password(command.getPassword());
    }
}

