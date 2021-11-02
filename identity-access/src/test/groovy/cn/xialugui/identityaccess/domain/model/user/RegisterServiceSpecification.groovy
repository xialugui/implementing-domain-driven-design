package cn.xialugui.identityaccess.domain.model.user

import cn.xialugui.identityaccess.domain.model.user.aggregate.User
import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository
import cn.xialugui.identityaccess.domain.model.user.valueobject.*
import cn.xialugui.identityaccess.resources.user.UserDetailsProjection
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

/**
 *
 * @author 夏露桂* @since 2021/8/9 20:03
 */
@Title("注册领域服务单元测试")
@Subject(RegisterService)
class RegisterServiceSpecification extends Specification {
    RegisterService registerService
    UserRepository userRepository = Mock()
    PasswordEncoder passwordEncoder = Mock()
    Username username = new Username("杜甫")
    Nickname nickname = new Nickname("杜甫")
    UserId userId = new UserId("dufu")
    //国破山河在，城春草木生。
    String password = "Gp3hz,Cccms."
    MobilePhone mobilePhone = new MobilePhone("13812346578")
    Email email = new Email("dufu@139.com")
    User user

    def setup() {
        user = new User(
                userId,
                username,
                nickname,
                email,
                mobilePhone,
                Password.encodeOf(password, passwordEncoder)
        )
        registerService = new RegisterService(passwordEncoder, userRepository)
    }

    def '注册时用户id不能重复'() {
        when: '注册用户'
        userRepository.findByNaturalId(userId) >> Optional.of({ return "dufu" } as UserDetailsProjection)
        register()
        then: '提示'
        def e = thrown(IllegalArgumentException)
        e.message == "用户id${userId}已被注册"
    }

    def '注册时用户名不能重复'() {
        when: '注册用户'
        repeatOf(null, null, username, null)
        register()
        then: '提示'
        def e = thrown(IllegalArgumentException)
        e.message == "用户${username}已被注册"
    }

    def '注册时邮箱不能重复'() {
        when: '注册用户'
        repeatOf(email, null, null, null)
        register()
        then: '提示'
        def e = thrown(IllegalArgumentException)
        e.message == "邮箱${email}已被注册"
    }

    def '注册时邮箱可以为空'() {
        when: "注册用户"
        repeatOf(null, null, null, null)
        register(null)
        then: '邮箱可以为空'
        noExceptionThrown()
    }

    def '注册时手机号不能重复'() {
        when: '注册用户'
        repeatOf(null, mobilePhone, null, null)
        register()
        then: '提示'
        def e = thrown(IllegalArgumentException)
        e.message == "手机号${mobilePhone}已被注册"
    }

    def "通过手机号注册时，根据手机号生成身份信息"() {
        given: "通过手机号注册"
        noRepeatForAll()
        when: "通过手机号注册"
        registerByMobilePhone(mobilePhone)
        then: "生成了正确的用户信息"
        1 * userRepository.save(_ as User)

    }


    def register(e = email) {
        registerService.register(
                userId,
                username,
                nickname,
                password,
                mobilePhone,
                email
        )
    }

    def noRepeat() {
        repeatOf(null, null, null, null)
    }

    def repeatOf(Email e, MobilePhone m, Username un, UserId ui) {
        userRepository.findByNaturalId(userId) >> (ui != null ? Optional.of(user) : Optional.empty())
        userRepository.findByUsername(username) >> (un != null ? Optional.of(user) : Optional.empty())
        userRepository.findByEmail(email) >> (e != null ? Optional.of(user) : Optional.empty())
        userRepository.findByMobilePhone(mobilePhone) >> (m != null ? Optional.of(user) : Optional.empty())
    }

    def noRepeatForAll() {
        userRepository.findByNaturalId(_ as UserId) >> Optional.empty()
        userRepository.findByUsername(_ as Username) >> Optional.empty()
        userRepository.findByEmail(_ as Email) >> Optional.empty()
        userRepository.findByMobilePhone(_ as MobilePhone) >> Optional.empty()
    }

    private void registerByMobilePhone(MobilePhone mobilePhone) {
        registerService.registerByMobilePhone(mobilePhone)
    }
}
