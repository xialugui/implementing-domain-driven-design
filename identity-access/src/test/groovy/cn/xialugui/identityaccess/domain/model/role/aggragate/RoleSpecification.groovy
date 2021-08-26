package cn.xialugui.identityaccess.domain.model.role.aggragate

import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleName
import spock.lang.*

import javax.validation.ConstraintViolation
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

/**
 *
 * @author 夏露桂
 * @since 2021/8/26 17:52
 */
@Title("角色单元测试")
@Subject(Role)
class RoleSpecification extends Specification {
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
        validator = validatorFactory.getValidator();
    }

    @Unroll("id：#id 不合法")
    def '角色id（2-16位数字和字母）'() {
        given: '初始化'
        Role role = new Role(id, null)
        when: '校验'
        Set<ConstraintViolation> constraintViolations = validator.validateProperty(role, 'roleId')
        then: '提示'
        with(constraintViolations) {
            size() == 1
            getAt(0).getMessageTemplate() == errorMessage
        }
        where:
        id                              || errorMessage
        null                            || NOT_NULL
        new RoleId(null)                || NOT_NULL
        new RoleId('')                  || PATTERN
        new RoleId('1')                 || PATTERN
        new RoleId('12123123123123112') || PATTERN
        new RoleId('1212312312312是')    || PATTERN
    }

    @Unroll("角色名：#name 不合法")
    def '角色名是2-16位数字、字符和中文'() {
        given: '初始化'
        Role role = new Role(null, name)
        when: "验证"
        Set<ConstraintViolation> constraintViolations = validator.validateProperty(role, 'name')
        then: "不合格提示"
        with(constraintViolations) {
            size() == 1
            getAt(0).getMessageTemplate() == errorMessage
        }
        where:
        name                              || errorMessage
        null                              || NOT_NULL
        new RoleName(null)                || NOT_NULL
        new RoleName('')                  || PATTERN
        new RoleName(' ')                 || PATTERN
        new RoleName('1')                 || PATTERN
        new RoleName('12312312313123133') || PATTERN
    }
}
