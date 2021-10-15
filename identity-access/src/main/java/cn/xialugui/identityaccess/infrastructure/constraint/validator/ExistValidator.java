package cn.xialugui.identityaccess.infrastructure.constraint.validator;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Type;

/**
 * 存在验证器
 *
 * @author 夏露桂
 * @since 2021/10/15 11:48
 */
@Slf4j
public abstract class ExistValidator<T> implements ConstraintValidator<Exist, T> {
    private final static String OBJECT = "object";

    @Override
    public boolean isValid(T value, ConstraintValidatorContext context) {
        if (null == value) {
            Type genericType = this.getClass().getTypeParameters()[0];
            Tag tag = genericType.getClass().getAnnotation(Tag.class);
            context.unwrap(HibernateConstraintValidatorContext.class)
                    .addMessageParameter(OBJECT, tag.value());
        }
        log.debug(context.getDefaultConstraintMessageTemplate());
        return null != value;
    }
}
