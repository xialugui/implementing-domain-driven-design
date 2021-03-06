package cn.xialugui.identityaccess.infrastructure.constraint.validator

import cn.xialugui.identityaccess.ValidatableSpecification
import cn.xialugui.identityaccess.domain.model.role.aggregate.Role
import cn.xialugui.identityaccess.domain.model.user.UserDomainService
import cn.xialugui.identityaccess.domain.model.user.aggregate.User
import spock.lang.Title
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import java.lang.reflect.Method

/**
 *
 * @author 夏露桂
 * @since 2021/10/15 16:57
 */
@Title("存在验证器说明")
class ExistValidatorSpecification extends ValidatableSpecification {

    @Unroll("输入：#inputObject，结果：#result")
    def '对象不存在时抛出异常'() {
        given: '建立验证方法和参数'
        UserDomainService testObject = new UserDomainService(null)
        Method method = UserDomainService.getMethod('addRole', User, Role)
        Set<ConstraintViolation> constraintViolations =
                validateParameters(testObject, method, new Object[]{user, role})
        expect: '提示'
        with(constraintViolations[0]) {
            message == result
        }
        where:
        user       | role       || result
        null       | new Role() || '用户不存在'
        new User() | null       || '角色不存在'

    }
}
