package cn.xialugui.sharedkernel.infrastructure.constraint.validator;

import cn.xialugui.sharedkernel.domain.model.Identifiable;
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
    private Exist constraintAnnotation;
    public static final String NOT_EXIST = "{cn.xialugui.identityaccess.NotExist.message}";

    @Override
    public void initialize(Exist constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (null == value ||
                (value instanceof Identifiable && null == (((Identifiable<?>) value).naturalId()))) {
            HibernateConstraintValidatorContext validatorContext = context.unwrap(HibernateConstraintValidatorContext.class);
            validatorContext.disableDefaultConstraintViolation();
            validatorContext.buildConstraintViolationWithTemplate("{" + constraintAnnotation.target().getSimpleName() + "}" + NOT_EXIST).addConstraintViolation();
            return false;
        }
        return true;
    }
}
