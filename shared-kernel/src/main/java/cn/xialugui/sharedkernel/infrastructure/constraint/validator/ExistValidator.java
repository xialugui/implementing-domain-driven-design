package cn.xialugui.sharedkernel.infrastructure.constraint.validator;

import lombok.SneakyThrows;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 存在验证器
 *
 * @author 夏露桂
 * @since 2021/10/15 11:48
 */

public class ExistValidator implements ConstraintValidator<Exist, Object> {
    private final static String OBJECT = "object";
    private Exist constraintAnnotation;

    @Override
    public void initialize(Exist constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Tag tag = constraintAnnotation.target().getAnnotation(Tag.class);
        if (null != tag) {
            HibernateConstraintValidatorContext validatorContext = context.unwrap(HibernateConstraintValidatorContext.class);
            validatorContext.addMessageParameter(OBJECT, tag.value());
        }
        return null != value;
    }
}
