package cn.xialugui.identityaccess

import spock.lang.Shared
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import javax.validation.executable.ExecutableValidator
import java.lang.reflect.Method


/**
 * 具有验证特性的规格说明
 * @author 夏露桂* @since 2021/9/7 10:20
 */
class ValidatableSpecification extends Specification {
    @Shared
    String PATTERN = '{javax.validation.constraints.Pattern.message}'
    @Shared
    String NOT_NULL = '{javax.validation.constraints.NotNull.message}'
    @Shared
    ValidatorFactory validatorFactory
    @Shared
    Validator validator
    /**
     * 方法验证器
     */
    @Shared
    ExecutableValidator executableValidator

    def setupSpec() {
        validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
        executableValidator = validator.forExecutables()
    }

    /**
     * 验证属性
     * @param object 对象
     * @param propertyName 属性名称，支持路径如："user.name"
     * @param groups 验证分组
     * @return 违反约束条件的集合
     */
    def <T> Set<ConstraintViolation<T>> validateProperty(T object,
                                                         String propertyName,
                                                         Class<?>... groups) {
        return validator.validateProperty(object, propertyName, groups)
    }

    /**
     * 验证对象
     * @param object 对象
     * @param groups 验证分组
     * @return 违反约束条件的集合
     */
    def <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        return validator.validate(object, groups)
    }

    /**
     *  验证属性的值
     * @param beanType 对象类型
     * @param propertyName 属性名
     * @param value 值
     * @param groups 验证分组
     * @return 违反约束条件的集合
     */
    def <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType,
                                                      String propertyName,
                                                      Object value,
                                                      Class<?>... groups) {
        return validator.validateValue(beanType, propertyName, value, groups)
    }

    /**
     * 验证方法参数
     * @param object 对象
     * @param method 方法
     * @param parameterValues 参数列表
     * @param groups 分组
     * @return 违反约束条件的集合
     */
    def <T> Set<ConstraintViolation<T>> validateParameters(T object,
                                                           Method method,
                                                           Object[] parameterValues,
                                                           Class<?>... groups) {
        return executableValidator.validateParameters(object, method, parameterValues, groups)
    }

    def <T> boolean violated(Set<ConstraintViolation<T>> constraintViolations) {
        if (null != constraintViolations && constraintViolations.size() > 0) {
            return true
        }
        return false

    }
/*
    def <T> void assertIfViolated(Set<ConstraintViolation<T>> constraintViolations, {
}

) {

}*/

    def <T> boolean ifViolated(Set<ConstraintViolation> constraintViolations, Closure action) {
        if (null != constraintViolations && constraintViolations.size() > 0) {
            action.call()
        }
        return true
    }

    def <T> boolean ifViolatedOrElse(Set<ConstraintViolation> constraintViolations, Closure action, Closure otherAction) {
        if (null != constraintViolations && constraintViolations.size() > 0) {
            action.call()
        } else {
            otherAction.call()
        }
        return true
    }


}