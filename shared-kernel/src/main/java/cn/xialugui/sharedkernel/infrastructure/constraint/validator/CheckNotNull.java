package cn.xialugui.sharedkernel.infrastructure.constraint.validator;

import java.lang.annotation.*;

/**
 * 检查对象及其自然ID否为空的注解。
 *
 * @author 夏露桂
 * @see CheckNotNullProcessor
 * @see Exist
 * @see ExistValidator
 * @since 2021/10/16 17:28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface CheckNotNull {
}
