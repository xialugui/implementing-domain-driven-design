package cn.xialugui.sharedkernel.infrastructure.constraint.validator;

import java.lang.annotation.*;

/**
 * @author 夏露桂
 * @since 2021/10/16 17:28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface CheckNotNull {
}
