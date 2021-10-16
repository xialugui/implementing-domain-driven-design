package cn.xialugui.identityaccess.infrastructure.constraint.validator;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 存在验证器
 *
 * @author 夏露桂
 * @since 2021/10/15 11:48
 */
@Slf4j
public class ExistValidator implements ConstraintValidator<Exist, Object> {
    private final static String OBJECT = "object";

    @SneakyThrows
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

/*        String result;
        if (value != null) {
//            result = value.logicName();

        } else {
            result = "对象";
        }
        HibernateConstraintValidatorContext validatorContext = context.unwrap(HibernateConstraintValidatorContext.class);
        validatorContext.addMessageParameter(OBJECT, result);*/
        return null != value;
    }
}
