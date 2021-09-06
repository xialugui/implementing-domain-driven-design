package cn.xialugui.identityaccess.domain.model.role.aggregate

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
        Set<ConstraintViolation> constraintViolations = validator.validateProperty(role, 'roleId.value')
        then: '提示'
        with(constraintViolations) {
            size() == 1
            getAt(0).getMessageTemplate() == errorMessage
        }
        where:
        id                              || errorMessage
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
        Set<ConstraintViolation> constraintViolations = validator.validateProperty(role, 'name.value')
        then: "不合格提示"
        with(constraintViolations) {
            size() == 1
            getAt(0).getMessageTemplate() == errorMessage
        }
        where:
        name                              || errorMessage
        new RoleName(null)                || NOT_NULL
        new RoleName('')                  || PATTERN
        new RoleName(' ')                 || PATTERN
        new RoleName('1')                 || PATTERN
        new RoleName('12312312313123133') || PATTERN
    }

    def '角色初始化时权限为空但不为null'() {
        when: '初始化角色'
        Role role = new Role(
                RoleId.random(),
                new RoleName("libai")
        )
        then: '权限为空但不为null'
        with(role) {
            permissionIds != null
            permissionIds.size() == 0
            roleId != null
            name.value == 'libai'
        }

    }
}
