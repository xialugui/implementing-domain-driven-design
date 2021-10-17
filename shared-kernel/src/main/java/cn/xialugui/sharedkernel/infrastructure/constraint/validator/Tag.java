package cn.xialugui.sharedkernel.infrastructure.constraint.validator;

import java.lang.annotation.*;

/**
 * @author 夏露桂
 * @since 2021/10/15 15:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Tag {
    String value() default "对象";
}
