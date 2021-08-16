package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

/**
 * 用户名（在这里使用中文便于阐述概念）
 * 显然用户名可以分为姓和名两个属性，它们是一组相关的属性，用户名不可或缺的组成部分。
 * 并不是任何属性都可以组合成值对象，
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@Embeddable
public final class Username extends ValueObject<Username> {
    /**
     * 如李白，姓李名白，字太白，号青莲居士。
     */
    private String 姓;
    private String 名;
    private String 字;
    private String 号;

    /**
     * 无参数构造函数是为一些框架准备的，比如Hibernate。
     * 客户端使用的是公有的构造函数，而不是这个隐藏的构造函数。
     * <p>
     * 注意：修饰符为{@code protected}
     */
    protected Username() {
        super();
    }

    public Username(String 姓, String 名, String 字, String 号) {
        set姓(姓);
        set名(名);
        set字(字);
        set号(号);
    }

    /**
     * 在这里我们没有使用
     * <p>
     * {@code this.字=username.get字}
     * <p>
     * 这种形式，而是通过调用私有的setter方法实现。该私有
     * 的setter方法向我们展示了一种自委派性，这是我们所推荐的。
     * <p>
     * 只有主构造函数才能使用自委派性来设置属性值， 除此之外， 其他方法都不能使用
     * setter方法。由于值对象中的所有setter方法都是私有的，消费方是没有机会直接调用这些
     * setter方法的。这是保持值对象不变性的两个重要因素。
     *
     * @param username 用户名值对象
     */
    public Username(Username username) {
        set姓(username.get姓());
        set名(username.get名());
        set字(username.get字());
        set号(username.get号());
    }

    public Username(String username) {
        set号(username);
    }

    /**
     * 一个对象的{@code setter}和{@code getter}方法并不见得只局限于设置对象
     * 的属性值，还可以进行断言操作，这对于通常的软件开发和{@code DDD}模型来说
     * 都是很重要的。
     * <p>
     * 对参数的合法性进行断言称为守卫，此时的断言保护着对象，使其处于一种合法的状态。
     *
     * @param 姓 姓
     */
    private void set姓(String 姓) {
        if (StringUtils.isEmpty(姓)) {
            throw new IllegalArgumentException("姓不能为空");
        }
        this.姓 = 姓;
    }

    /**
     * 无副作用函数指的是只产生输出而不改变对象状态的函数。
     * 对不变对象而言，所有的方法都必须是无副作用的，因为它们不能破坏值对象的不可变性。
     * 这种特性类似于不可变性，但我们将其中不可变性中分离出来，将其称之为无副作用函数。
     * 函数式的编程语言通常都强制性地保留了这种特性。
     * <p>
     * {@code
     * Username username = new Username("李","白","太白","青莲居士");
     * }
     * <p>调用{@code username.with号("居士")}，
     * <p>返回的是新的值对象。
     * <p>
     * 此函数的效果和可替换性是一样的，不过更具表达性。此外，该方法还捕获了重要的领域
     * 业务逻辑，从而避免将这些逻辑泄漏到客户端。
     * <p>
     * 还有一点是当值对象方法参数是实体时，是否允许对实体对象进行修改，如果修改，该方法
     * 还是无副作用吗？这个问题参考{@link MobilePhone#mobilePhoneOf(User user)}
     *
     *
     * <p>
     * <p>
     * <p>
     * 可替换性参考{@link Nickname}
     *
     * @param 号 号
     * @return 用户名
     */
    public Username with号(String 号) {
        if (StringUtils.isEmpty(号)) {
            throw new IllegalArgumentException("号不能为空");
        }
        return new Username(get姓(), get名(), get字(), 号);
    }

    /**
     * 可以看到我们没有使用JavaBean的命名规范，即为方法加上"get"前缀。
     * 这种方式使得代码与通用语言保持一致。使用{@code getFullName()}
     * 只是一个技术上的用法，但是{@code fullName()}则是一种流畅的、
     * 可读的语言表达。
     * <p>
     * <p>
     * 我们认为{@code JavaBean}规范对于对象设计来说存在负面影响，同时它也没有体现
     * 出领域驱动设计的原则。让我们来看看{@code JavaBean}规范之前的那些{@code API}。
     * 比如：
     * <p>
     * {@link java.lang.String}
     * <p>
     * 类中只有为数不多的查询方法使用了get前缀。多数查询方法的命名都是非常流畅的。
     * 比如
     * <p>
     * {@code charAt().compareTo().concat().contains().endsWith()
     * indexOf().length().replace().startsWith()，substring()}
     * <p>
     * 等。这里并没有多少{@code JavaBean}的坏味道。当然，只是这一个例子是不能说明问题的。
     * 然而，{@code JavaBean}规范出来之后的{@code API}的确缺少了很多流畅性。
     * 一种流畅的、可读的语言表达方式是值得拥有的。
     * <p>
     * 对于一些使用了{@code JavaBean}规范的工具。我们是有解决办法的。比如，
     * <p>
     * {@code Hibernate}
     * <p>
     * 支持字段级别的访问（对象属性）。因此在使用{@code Hibernate}时，我们可以根据自己的需要
     * 来命名方法的名字， 而不会对持久化造成影响。然而，对于其他的一些工具. 要使用富有表达性的接口可能就会有问题了。比
     * 如在使用{@code Java EL或OGNL}时. 你很有可能得不到期望的结果。当然，我们还可以使
     * 用其他的方式，比如数据传输对象{@code Data Transfer Object，DTO}.该对象提供了{@code getter}方法
     * <p>
     * <p>
     * 它将值对象的属性通过{@code getter}方法暴露给用户界面。{@code DTO}是一个常用的模式。
     * 但是从技术上来说并没有多大必要， 因此。{@code DTO}也不见得是最好的选择。此时，
     * 我们可以考虑使用展现模型{@code Presentation Model}。由于展现模型实现了适配器模式，
     * 它可以向使用{@code EL}的视图层提供{@code getter}方法。如果以上方法都失败了。那么你不得不
     * 回到原地，乖乖地在领域对象中使用{@code getter}方法。即便如此.在设计值对象时，
     * 我们也不应该完全地遵循{@code JavaBean}规范。比如，JavaBean规范要求我们提供公有的setter方法。
     * 而这将违背值对象的不变性特征。
     *
     * @return 字符串全称
     */
    public String fullName() {
        return get姓() + get名() + "，字" + get字() + "，号" + get号();
    }

    /**
     * 两个值对象的相等性由其类型和属性决定
     *
     * @param other 另一对象
     * @return 全相等返回true
     */
    @Override
    protected boolean equalsTo(Username other) {
        return StringUtils.equals(get姓(), other.get姓()) &&
                StringUtils.equals(get名(), other.get名()) &&
                StringUtils.equals(get字(), other.get字()) &&
                StringUtils.equals(get号(), other.get号())
                ;
    }

    @Override
    public String toString() {
        return 字;
    }
}
