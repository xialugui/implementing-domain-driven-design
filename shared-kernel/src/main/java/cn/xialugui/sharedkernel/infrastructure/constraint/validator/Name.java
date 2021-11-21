package cn.xialugui.sharedkernel.infrastructure.constraint.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameValidator.class)
@Documented
public @interface Name {
    /**
     * message是必须的，它返回错误信息。
     *
     * @return 错误信息
     */
    String message() default "{cn.xialugui.sharedkernel.Name.message}";

    /**
     * 正则表达式
     *
     * @return 表达式
     */
    String regexp() default "^[\\u4e00-\\u9fa5_a-zA-Z0-9]{2,16}$";

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