package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * 用户ID值对象。
 * <p>
 *  <ul>
 *      <li>
 *          度量或描述<br>
 *          用户ID描述了用户的id概念。
 *      </li>
 *      <li>不可变性见{@link #UserId(String id)},{@link #UserId()}</li>
 *  </ul>
 * </p>
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Embeddable
public final class UserId extends ValueObject<UserId> {

    /**
     * 我们不建议在值对象中维持对实体对象的引用，在引用对象状态改变的情况下
     * 破坏了值对象的不可变性。
     * <p>
     * {@code private Role role;}
     * </p>
     */
    private String id;

    /**
     * 保护的构造函数，确保只有一个公开的初始化构造函数{@link #UserId(String id)}。
     */
    protected UserId() {

    }


    public UserId(String id) {
        setId(id);
    }

    /**
     * {@code private}访问修饰符保证除了构造函数外，任何方法都不能对该对象属性状态进行修改。
     */
    private void setId(String id) {
        this.id = id;
    }

    @Override
    protected boolean equalsTo(UserId other) {
        return false;
    }
}
