package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 邮箱
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
public final class Email extends ValueObject<Email> {
    private String email;

    public Email(String email) {
        setEmail(email);
    }


    @Override
    protected boolean equalsTo(Email other) {
        return false;
    }
}
