package cn.xialugui.identityaccess.domain.model.role.aggregate

import cn.xialugui.identityaccess.ValidatableSpecification
import cn.xialugui.identityaccess.domain.model.permission.aggregate.Permission
import cn.xialugui.identityaccess.domain.model.permission.valueobject.PermissionId
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleName
import com.lugew.winsin.core.exception.Exception
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import javax.validation.Validation

/**
 *
 * @author 夏露桂* @since 2021/8/26 17:52
 */
@Title("角色单元测试")
@Subject(Role)
class RoleSpecification extends ValidatableSpecification {

    @Unroll("id：#id 不合法")
    def '角色id（2-16位数字和字母）'() {
        given: '初始化'
        Role role = new Role(id, null)
        when: '校验'
        Set<ConstraintViolation> constraintViolations = validator.validateProperty(role, 'naturalId.value')
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
            naturalId() != null
            name.value == 'libai'
        }

    }

    def '添加权限时，权限或其自然id不能为null'() {
        given: '初始化角色和权限'
        Role role = new Role(
                RoleId.random(),
                new RoleName("libai")
        )
        when: '添加权限'
        role.addPermission(permission)
        then: '权限为null或者permissionId不存在时抛出异常'
        thrown(exception)

        where:
        permission                 || exception
        null                       || Exception
        new Permission(null, null) || Exception

    }

    def '修改权限，直接将权限覆盖'() {
        given: '初始化角色和权限'
        Role role = new Role(
                RoleId.random(),
                new RoleName("libai")
        )
        Permission permission = new Permission(PermissionId.random(), "test")
        role.addPermission(permission)
        when: '修改权限'

        role.updatePermissions(permissions)
        then: '权限已被修改'
        with(role) {
            permissionIds.size() == (permissions == null ? 0 : permissions.size())
            if (null != permissions) {
                permissions.forEach(p -> {
                    permissionIds.contains(p)
                })
            }
        }
        where:
        permissions                                                                                      || exception
        [new Permission(PermissionId.random(), "test1"), new Permission(PermissionId.random(), "test2")] || Exception
        null                                                                                             || Exception

    }
}
