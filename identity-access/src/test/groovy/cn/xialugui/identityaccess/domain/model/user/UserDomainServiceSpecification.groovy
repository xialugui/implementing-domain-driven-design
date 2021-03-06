package cn.xialugui.identityaccess.domain.model.user

import cn.xialugui.identityaccess.ValidatableSpecification
import cn.xialugui.identityaccess.domain.model.role.aggregate.Role
import cn.xialugui.identityaccess.domain.model.user.aggregate.User
import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository
import cn.xialugui.identityaccess.domain.model.user.valueobject.*
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Shared
import spock.lang.Subject
import spock.lang.Title

import javax.validation.ConstraintViolation
import java.lang.reflect.Method

/**
 *
 * @author 夏露桂
 * @since 2021/9/10 17:15
 */
@Title("用户领域服务单元测试")
@Subject(UserDomainService)
class UserDomainServiceSpecification extends ValidatableSpecification {
    UserRepository userRepository = Mock()
    PasswordEncoder passwordEncoder = Mock()
    Username username = new Username("杜甫")
    Nickname nickname = new Nickname("杜甫")
    UserId userId = new UserId("dufu")
    //国破山河在，城春草木生。
    String password = "Gp3hz,Cccms."
    MobilePhone mobilePhone = new MobilePhone("13812346578")
    Email email = new Email("dufu@139.com")

    UserDomainService domainService
    @Shared
    User user

    def setup() {
        user = new User(userId, username, nickname, email, mobilePhone, new Password(password, passwordEncoder))
        domainService = new UserDomainService(userRepository)
    }

    /**
     * 参数null验证依赖的是javax.validation，它是稳定可靠的，所以
     * 在之后的方法中，我们便不再验证，这里只做示范用。原则上我们只验证自定义
     * 约束。
     */
    def '用户增加角色时，参数不为null'() {
        given: '建立验证方法和参数'
        Method method = UserDomainService.getMethod('addRole', User, Role)
        Set<ConstraintViolation> constraintViolations =
                validateParameters(domainService, method, new Object[]{userInput, roleInput})
        expect: '提示'
        ifViolated(constraintViolations, {
            with(constraintViolations[0]) {
                message == result
            }
        })
        where:
        roleInput    | userInput    || result
        null as Role | User.RANDOM  || '角色不存在'
        Role.RANDOM  | null as User || '用户不存在'
    }

/*    def '用户增加角色，重复角色直接覆盖，未重复则添加'() {
        given:
        RoleId roleId = RoleId.random()
        Role role = new Role(roleId, new RoleName("test"))
        when: '添加角色'


    }*/

}
