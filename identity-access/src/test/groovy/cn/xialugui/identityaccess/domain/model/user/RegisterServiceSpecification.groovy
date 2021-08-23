package cn.xialugui.identityaccess.domain.model.user

import cn.xialugui.identityaccess.domain.model.user.aggregate.User
import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository
import cn.xialugui.identityaccess.domain.model.user.valueobject.*
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
    //国破山河在，城春草木生。
    String password = "Gp3hz,Cccms."
    MobilePhone mobilePhone = new MobilePhone("13812346578")
    Email email = new Email("dufu@139.com")
    User user

    def setup() {
        user = new User(
                UserId.uuid(),
                username,
                nickname,
                email,
                mobilePhone,
                Password.encodeOf(password, passwordEncoder)
        )
        registerService = new RegisterService(passwordEncoder, userRepository)
    }

    def '注册时用户名不能重复'() {
        when: '注册用户'
        userRepository.findByUsername(username) >> Optional.of(user)
        register()
        then: '提示'
        def e = thrown(IllegalArgumentException)
        e.message == "用户${username}已被注册"
    }

    def '注册时邮箱不能重复'() {
        when: '注册用户'
        userRepository.findByUsername(username) >> Optional.empty()
        userRepository.findByEmail(email) >> Optional.of(user)
        register()
        then: '提示'
        def e = thrown(IllegalArgumentException)
        e.message == "邮箱${email}已被注册"
    }

    def '注册时手机号不能重复'() {
        when: '注册用户'
        userRepository.findByUsername(username) >> Optional.empty()
        userRepository.findByEmail(email) >> Optional.empty()
        userRepository.findByMobilePhone(mobilePhone) >> Optional.of(user)
        register()
        then: '提示'
        def e = thrown(IllegalArgumentException)
        e.message == "手机号${mobilePhone}已被注册"
    }


    private void register() {
        registerService.register(
                username,
                nickname,
                password,
                mobilePhone,
                email
        )
    }
}
