package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.validator.GenericValidator;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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

    @Enumerated(EnumType.STRING)
    private EmailType emailType;

    protected Email() {
    }

    public Email(String email, EmailType emailType) {
        setEmail(email);
        setEmailType(emailType);
    }

    public Email(String email) {
        setEmail(email);
        setEmailType(EmailType.of(email));
    }

    private void setEmail(String email) {
        if (!GenericValidator.isEmail(email)) {
            throw new IllegalArgumentException("邮箱格式不正确");
        }
        this.email = email;
    }


    @Override
    protected boolean equalsTo(Email other) {
        return false;
    }

    @Override
    public String toString() {
        return email;
    }
}
