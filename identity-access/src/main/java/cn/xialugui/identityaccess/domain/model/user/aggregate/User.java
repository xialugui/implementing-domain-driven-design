package cn.xialugui.identityaccess.domain.model.user.aggregate;

import cn.xialugui.identityaccess.domain.ValidationNotificationHandler;
import cn.xialugui.identityaccess.domain.model.AbstractAggregateRoot;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import cn.xialugui.identityaccess.domain.model.user.UserValidator;
import cn.xialugui.identityaccess.domain.model.user.event.UserCreatedEvent;
import cn.xialugui.identityaccess.domain.model.user.valueobject.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户聚合
 * <p>
 * 将实体和值对象在一致性边界之内组成聚合是DDD战术指导中最难理解的。
 * 聚合不仅是将共享父类、密切关联的对象组成对象树，而且必须遵守相应的规则。
 * 聚合模式讨论的是对象组合和信息隐藏，它还包含了一致性边界和事务。
 * </p>
 *
 * @author 夏露桂
 * @since 2021/7/14 17:50
 */
@Getter
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PROTECTED)
public final class User extends AbstractAggregateRoot<User, UserId> {
    /**
     * 有人会疑惑，为什么唯一标识不适用简单的字符串。
     * <p>
     * 唯一标识会在很多地方使用，不同的上下文，不同的实体。此时使用强类型使它们
     * 更容易被识别。
     * </p>
     * <p>
     * 在此处，我们没有使用数据库的唯一约束来实现唯一性。考虑到易用性，我们在{@link cn.xialugui.identityaccess.domain.model.role.aggregate.Role#roleId}
     * 中使用{@code Hibernate}的{@link  org.hibernate.annotations.NaturalId}来实现
     * 逻辑id的唯一约束。
     * </p>
     */

    @Embedded
    private Username username;
    @Embedded
    private Nickname nickname;
    @Embedded
    private Email email;
    @Embedded
    private MobilePhone mobilePhone;
    @Embedded
    private Password password;


    /**
     * 聚合的设计应该尽可能地小，通过唯一标识符来引用其他聚合。
     * 但是在项目初期，我们可以先使用直接引用对象的方式。
     * <p>
     * {@code @ManyToMany(targetEntity = Role.class)}
     * <br>
     * {@code private Set<Role> Roles;}
     * </p>
     * 对象引用带来的问题是每次加载的内存消耗，如果集合数量庞大，那么
     * 代价是非常巨大的。虽然Hibernate拥有懒加载机制，但我们仍不建议使用。
     * <p>
     * 当然，对象引用的好处是方便UI层显示，但有时坚持原则的好处更大。
     * <p>
     * 在模型中只使用唯一标识来引用对象的缺点在于∶ 在UI层，要组装多个聚合并予
     * 以显示将变得非常困难，我们不得不使用多个资源库。此时，如果对聚合的查询导致
     * 了性能问题，那么我们可以考虑theta联合查询或者CQRS。比如，Hibernate就支
     * 持theta联合查询。而如果CQRS和theta联合查询都不能满足我们的需求，那么就
     * 需要在标识引用和直接引用之间折中考虑了。
     */
    @ElementCollection
    @NotNull
    private Set<RoleId> roleIds;

    /**
     * 我们有时必须保证，参数的非空性，这时我们可以使用类似唯一标识维持稳定性的方式进行自封装。参考{@link #setUserId(UserId)},
     * {@link #setUsername(Username)}。
     * <br>
     * 对于一些复杂的情况，我们可以使用工厂。工厂参考{@link }
     *
     * @param userId      用户id
     * @param username    用户名
     * @param nickname    昵称
     * @param email       邮箱
     * @param mobilePhone 手机号
     * @param password    密码
     */
    public User(UserId userId, Username username, Nickname nickname, Email email, MobilePhone mobilePhone, Password password) {
        setUserId(userId);
        setUsername(username);
        setNickname(nickname);
        setEmail(email);
        setMobilePhone(mobilePhone);
        setPassword(password);
        setRoleIds(new HashSet<>());
        /*
          发布领域事件
         */
        andEvent(new UserCreatedEvent(this.id(), username));
    }

    private void setUsername(Username username) {
        if (username == null) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        this.username = username;
    }

    /**
     * 我们可以将标识的setter方法向客户隐藏起来。我们也可以在setter方法种添加逻辑以确保标
     * 识在已经存在的情况下不会再被更新， 比如可以使用一些断言语句。这种方式是良好自封装性的体现。
     * 自封装性要求无论以哪种方式访问数据，即使从对象内部访问数据，都必须通过getter和setter方法。
     *
     * @param userId 用户id
     */
    private void setUserId(UserId userId) {
        if (null != getId()) {
            throw new IllegalArgumentException("用户id不能更改");
        }
        if (null == userId) {
            throw new IllegalArgumentException("用户id不能为空");
        }
        setId(userId);
    }

    /**
     * 自封装为对象的实例变量和类变量提供了一层抽象。使我们可以方便地在对象中访问其所引用对象的属性。
     * 更重要的是，自封装性使验证变得非常简单。这种设计还可以在{@link Password#Password(String, PasswordEncoder)}中看到。
     */
    public void setNickname(Nickname nickname) {
        if (null == nickname) {
            throw new IllegalArgumentException("昵称不能为空");
        }
        this.nickname = nickname;
    }

    public void changeNickname(Nickname nickname) {
        setNickname(nickname);
    }

    /**
     * 虽然有时实体中的所有单个属性都是合法的， 但是这并不意味着整个实体就
     * 是合法的。要验证整个实体，我们需要访问整个对象的状态——所有对象属性。
     * 由于验证逻辑需要访问实体的所有状态，有人可能会直接将验证逻辑嵌入到
     * 实体对象中。这里我们需要注意了，更多的时候验证逻辑比领域对象本身变化还
     * 快，而将验证逻辑嵌入在领域对象中也使领域对象承担了太多的职责。
     * 此时我们可以创建一个单独的组件来完成模型验证。任何继承自{@link cn.xialugui.identityaccess.domain.model.Entity}的类
     * 都可以安全地调用{@link cn.xialugui.identityaccess.domain.model.Entity#validate(ValidationNotificationHandler)}方法。
     *
     * @param validationNotificationHandler 验证通知
     */
    @Override
    public void validate(ValidationNotificationHandler validationNotificationHandler) {
        new UserValidator(this, validationNotificationHandler).validate();
    }

    public void addRole(RoleId roleId) {
        getRoleIds().add(roleId);
    }
}

