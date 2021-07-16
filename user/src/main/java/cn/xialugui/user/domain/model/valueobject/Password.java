package cn.xialugui.user.domain.model.valueobject;

import lombok.Getter;
import lombok.Setter;

/**
 * 密码
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter
public final class Password extends SinglePropertyValueObject<Password> {
    /**
     * 数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间。
     */
    private final String regularExpression = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

    public Password(String password) {
        if (!password.matches(regularExpression)) {
            throw new IllegalArgumentException("密码非法");
        }
        setValue(password);
    }
}
