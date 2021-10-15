package cn.xialugui.identityaccess.infrastructure.constraint.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 夏露桂
 * @since 2021/10/15 15:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Tag {
    String value() default "对象";
}
