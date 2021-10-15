package cn.xialugui.identityaccess.infrastructure.constraint.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistValidator.class)
@Documented
public @interface Exist {
    /**
     * message是必须的，它返回错误信息。
     *
     * @return 错误信息
     */
    String message() default "{cn.xialugui.identity-access.Exist.message}";

    /**
     * 约束验证分组
     *
     * @return 分组
     */
    Class<?>[] groups() default {};

    /**
     * payload字段是Jakarta Bean Validation API使用的。
     *
     * @return 负载
     */
    Class<? extends Payload>[] payload() default {};

}