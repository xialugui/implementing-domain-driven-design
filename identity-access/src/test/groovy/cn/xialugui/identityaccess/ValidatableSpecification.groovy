package cn.xialugui.identityaccess

import spock.lang.Shared
import spock.lang.Specification

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory


/**
 * 具有验证特性的规格说明
 * @author 夏露桂
 * @since 2021/9/7 10:20
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

    def setupSpec() {
        validatorFactory = Validation.buildDefaultValidatorFactory()
        validator = validatorFactory.getValidator()
    }

    def <T> Set<ConstraintViolation<T>> validateProperty(T object,
                                                         String propertyName,
                                                         Class<?>... groups) {
        return validator.validateProperty(object, propertyName, groups)
    }

    def <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
        return validator.validate(object, groups)
    }

    def <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType,
                                                      String propertyName,
                                                      Object value,
                                                      Class<?>... groups) {
        return validator.validateValue(beanType, propertyName, value, groups)
    }

}