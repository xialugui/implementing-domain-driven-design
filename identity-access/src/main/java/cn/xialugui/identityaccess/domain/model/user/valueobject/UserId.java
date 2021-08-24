package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.Identifier;
import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户ID值对象。
 * <p>
 *  <ul>
 *      <li>
 *          度量或描述<br>
 *          用户ID描述了用户的id概念。
 *      </li>
 *      <li>不可变性见{@link #UserId(String userId)},{@link #UserId()}</li>
 *  </ul>
 * </p>
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Embeddable
public final class UserId extends ValueObject<UserId> implements Identifier {
    private static final Pattern PATTERN = Pattern.compile("^[0-9a-zA-Z]{2,16}");
    private static final char[] chars = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    };
    /**
     * 我们不建议在值对象中维持对实体对象的引用，在引用对象状态改变的情况下
     * 破坏了值对象的不可变性。
     * <p>
     * {@code private Role role;}
     * </p>
     */
    private String userId;

    /**
     * 保护的构造函数，确保只有一个公开的初始化构造函数{@link #UserId(String id)}。
     */
    protected UserId() {

    }

    /**
     * 我们可以使用ID中的创建时间向外界暴露，此外我们也可以
     * 使用其它值对象来表示。
     */
    //private LocalDateTime createdDate;
    public UserId(String userId) {
        setUserId(userId);
    }

    public static UserId random() {
        StringBuilder result = new StringBuilder();
        int max = chars.length;
        for (int i = 0; i < 16; i++) {
            result.append(chars[(int) (Math.random() * max)]);
        }
        return new UserId(result.toString());
    }

    /**
     * {@code private}访问修饰符保证除了构造函数外，任何方法都不能对该对象属性状态进行修改。
     */
    private void setUserId(String userId) {
        Matcher matcher = PATTERN.matcher(userId);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("用户id只能是2-16位数字或字母");
        }
        this.userId = userId;
    }

    @Override
    protected boolean equalsTo(UserId other) {
        return getUserId().equals(other.getUserId());
    }

    @Override
    public String toString() {
        return this.userId;
    }
}
