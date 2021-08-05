package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * 邮箱
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@Embeddable
public final class Email extends ValueObject<Email> {
    private String email;
    private EmailType emailType;

    protected Email() {
    }

    public Email(String email, EmailType emailType) {
        setEmailType(emailType);
        setEmail(email);
    }


    @Override
    protected boolean equalsTo(Email other) {
        return false;
    }
}
