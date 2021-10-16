package cn.xialugui.identityaccess.infrastructure.constraint.validator;

import java.lang.annotation.*;

/**
 * @author 夏露桂
 * @since 2021/10/16 13:47
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParametersExist {
}
