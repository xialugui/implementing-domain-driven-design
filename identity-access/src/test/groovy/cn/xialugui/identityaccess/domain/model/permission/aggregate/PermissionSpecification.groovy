package cn.xialugui.identityaccess.domain.model.permission.aggregate

import cn.xialugui.identityaccess.ValidatableSpecification
import cn.xialugui.identityaccess.domain.model.permission.valueobject.PermissionId
import org.hibernate.validator.internal.engine.path.PathImpl
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

import javax.validation.ConstraintViolation

/**
 *
 * @author 夏露桂
 * @since 2021/9/7 10:15
 */
@Subject(Permission)
@Title("权限规格说明")
class PermissionSpecification extends ValidatableSpecification {
    @Unroll("id:#id,name:#name")
    def '验证权限字段符合规则'() {
        given: '初始化'
        Permission permission = new Permission(id, name)
        when: '验证不能为null'
        Set<ConstraintViolation> constraintViolations = validate(permission)
        then: '提示'
        ifViolated(constraintViolations) {
            with(constraintViolations[0]) {
                messageTemplate == errorMessage
                propertyPath == PathImpl.createPathFromString(path)
            }
        }
        where:
        id                                    | name                 || path              || errorMessage || size
        null                                  | 'permissionName'     || 'naturalId'       || NOT_NULL     || 1
        new PermissionId(null)                | 'permissionName'     || 'naturalId.value' || NOT_NULL     || 1
        new PermissionId('')                  | 'permissionName'     || 'naturalId.value' || PATTERN      || 1
        new PermissionId('1')                 | 'permissionName'     || 'naturalId.value' || PATTERN      || 1
        new PermissionId('abcdef12345678911') | 'permissionName'     || 'naturalId.value' || PATTERN      || 1

        new PermissionId('abcdef1234567891')  | null                 || 'name'            || NOT_NULL     || 1
        new PermissionId('abcdef1234567891')  | ''                   || 'name'            || PATTERN      || 1
        new PermissionId('abcdef1234567891')  | 'p'                  || 'name'            || PATTERN      || 1
        new PermissionId('abcdef1234567891')  | 'abcdef123456789112' || 'name'            || PATTERN      || 1

        new PermissionId('abcdef1234567891')  | 'permissionName'     || _ as String       || PATTERN      || 0

    }
}
