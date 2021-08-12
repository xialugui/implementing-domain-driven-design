package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.passay.*;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    private final static PasswordValidator validator = new PasswordValidator(
            //长度8~16
            new LengthRule(8, 16),
            //大写字母最少一位
            new CharacterRule(EnglishCharacterData.UpperCase, 1),
            //小写字母最少一位
            new CharacterRule(EnglishCharacterData.LowerCase, 1),
            //数字最少一位
            new CharacterRule(EnglishCharacterData.Digit, 1),
            //特殊字符最少一位
            new CharacterRule(EnglishCharacterData.Special, 1),
            //禁止连续5位连续字母如：abcde
            new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
            //禁止连续5位连续数字如：12345
            new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
            //禁止连续5位连续美式键盘位如：qwerty
            new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),
            //禁止空白字符
            new WhitespaceRule()
    );

    /**
     * 自封装又被一些人称为契约式设计、前置条件检查、防御式编程。有些人并不同一这种方式，另有些认为对
     * null和空字符串的检查是可以接受的，但是没有必要验证字符串长度、数值范围和
     * 格式等信息。另外，还有人认为将对字符串长度和数值范围的验证放在数据库中是最好的办法，
     * 因为这些功能并不属于领域对象。有时我们没有必要对字符串的长度做出检查，此时可以定义一个拥有足够宽
     * 度的数据库列，比如在MySQL数据库中，我们可以建立长度约束。然而，对于有些数据库来说，
     * 这种方法便不见得可行了。在MySQL中，最大的行宽为65，535字节，请注意，这里是行宽，
     * 而不是列宽。如果我们将其中一个列定义为最大宽度为65，535的VARCHAR类型，那么其他的列便没有存放空间了。根
     * 据数据库中所定义VARCHAR列的数量，我们需要对每一列的宽度进行限制。在
     * 这种情况下，我们可能需要将某些列定义为TEXT类型，因为TEXT列和BLOB列
     * 存储在不同的块中。因此，对于不同的数据库，我们需要找到适当的限制列宽的方
     * 法， 以避免在领域模型中对字符串长度进行验证。如果对象的属性有可能超过列宽，那么此时在模型中进行长度验证便是有必
     * 要的了。此时最好的方式便是将长度验证放在前置条件中。
     *
     * @param password        密码
     * @param passwordEncoder 密码加密器
     */
    public Password(String password, PasswordEncoder passwordEncoder) {
        RuleResult result = validator.validate(new PasswordData(password));
        if (!result.isValid()) {
            StringBuilder errorMessage = new StringBuilder();
            validator.getMessages(result).forEach(errorMessage::append);
            throw new IllegalArgumentException(errorMessage.toString());
        }
        setPassword(passwordEncoder.encode(password));
    }

    public static Password encodeOf(String password, PasswordEncoder passwordEncoder) {
        return new Password(password, passwordEncoder);
    }

    @Override
    protected boolean equalsTo(Password other) {
        return false;
    }
}
