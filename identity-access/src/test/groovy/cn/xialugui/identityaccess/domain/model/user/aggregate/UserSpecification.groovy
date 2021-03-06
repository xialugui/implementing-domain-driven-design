package cn.xialugui.identityaccess.domain.model.user.aggregate

import cn.xialugui.identityaccess.ValidatableSpecification
import cn.xialugui.identityaccess.domain.model.role.aggregate.Role
import cn.xialugui.identityaccess.domain.model.user.valueobject.Password
import cn.xialugui.identityaccess.domain.model.user.valueobject.UserId
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

import javax.validation.ConstraintViolation
import java.lang.reflect.Method

/**
 *
 * @author 夏露桂* @since 2021/7/15 18:03
 */
@Title("用户聚合根的单元测试")
@Subject(User)
class UserSpecification extends ValidatableSpecification {

    @Unroll('密码：#password，结果：#result')
    def '创建用户时，密码必须合规'() {
        when: '创建用户'
        User user = new User(
                password: new Password(password, null)
        )
        then: '不合规则抛出异常'
        thrown(result)

        where: '用例'
        password      || result
        '12'          || IllegalArgumentException
        '12123123123' || IllegalArgumentException
    }
    /**
     * 逻辑主键赋值方法的访问方式为protected，外部无法调用，所以此测试
     * 无意义
     * @return
     */
/*    def '用户id不能修改'() {
        when: '创建用户'
        User user = new User(
                userId: new UserId("123")
        )
        user.naturalId(new UserId("213"))
        then: '抛出异常，不允许修改'
        thrown(IllegalArgumentException)
    }*/

    def '添加用户时，用户不能为空'() {
        given: '创建用户和角色'
        User user = new User(
                userId: new UserId("123")
        )
        Role role = new Role()

        when: '执行方法'
        Method method = User.getMethod('addRole', Role)
        Set<ConstraintViolation> constraintViolations =
                validateParameters(user, method, new Object[]{role})
        then: '提示'
        ifViolated(constraintViolations, {
            with(constraintViolations[0]) {
                message == '角色不存在'
            }
        })
    }
}
