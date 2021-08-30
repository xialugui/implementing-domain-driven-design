package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public final class MobilePhone extends ValueObject<MobilePhone> {
    private String mobilePhone;
    @Enumerated(EnumType.STRING)
    private MobilePhoneType mobilePhoneType;

    private static final String REGEX = "^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public MobilePhone(String mobilePhone) {
        setMobilePhone(mobilePhone);
        setMobilePhoneType(MobilePhoneType.of(mobilePhone));
    }

    private void setMobilePhone(String mobilePhone) {
        Matcher matcher = PATTERN.matcher(mobilePhone);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("不符合手机号规范");
        }
        this.mobilePhone = mobilePhone;
    }

    /**
     * 获取用户电话号码。
     * <p>
     * 此方法的调用示例：
     * <p>
     * {@code String mobilePhone = mobilePhone.mobilePhoneOf(user)}
     * <p>
     * 此方法中值对象对实体产生了引用，且存在以下问题
     * <ul>
     *     <li>
     *      MobilePhone值对象不仅依赖于User对象，而且还试图去理解该实体的内部状态。
     *     </li>
     *     <li>
     *      阅读本段代码的人并不知道使用了User的那些部分。这种表达方式并不明确，从而
     *      降低了模型的清晰性。更好的方式是只传入需要用到的User属性。
     *     </li>
     *     <li>
     *      更重要的是，在将实体作为参数的值对象方法中，很难看出该方法是否会对实体进行
     *      修改，测试也变得非常困难。即便一个值对象承诺不会修改实体，我们也很难证明这
     *      一点。
     *     </li>
     *
     * </ul>
     * 基于以上几点，参数应该是值对象：
     * <p>
     * {@code String mobilePhone = mobilePhone.mobilePhoneOf(user.getMobilePhone())}
     * 还有一点需要注意，返回值的类型不应该是基本类型，而应该返回值对象{@code MobilePhone}。
     * <p>
     * 基本对象是无法被赋予特定领域无副作用函数的。
     * <p>
     * 但是，并不是所有东西都适合被当作值对象的，比如简单的布尔或者数值类型，它们已经可以完整地表示
     * 其领域含义了，这时候无需将其封装成值对象。
     *
     * @param user 用户
     * @return 电话号码字符串
     */
    @Deprecated
    public String mobilePhoneOf(User user) {
        return user.getMobilePhone().getMobilePhone();
    }

    @Override
    protected boolean equalsTo(MobilePhone other) {
        return false;
    }
}
