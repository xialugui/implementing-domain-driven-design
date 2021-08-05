package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * 密码
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public final class Password extends ValueObject<Password> {
    private String password;
    /**
     * 数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间。
     */
    private final String regularExpression = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    public Password(String password) {
        if (!password.matches(regularExpression)) {
            throw new IllegalArgumentException("密码非法");
        }
        setPassword(password);
    }

    @Override
    protected boolean equalsTo(Password other) {
        return false;
    }
}
