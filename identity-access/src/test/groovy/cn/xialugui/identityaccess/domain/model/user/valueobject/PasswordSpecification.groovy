package cn.xialugui.identityaccess.domain.model.user.valueobject

import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

/**
 *
 * @author 夏露桂* @since 2021/8/23 13:58
 */
@Title("密码值对象单元测试")
@Subject(Password)
class PasswordSpecification extends Specification {
    PasswordEncoder passwordEncoder = Mock()
    Password password

    @Unroll("密码：#inputString 非法，规则：#errorMessage")
    def "密码需符合规则"() {
        when: "生成密码"
        password = Password.encodeOf(inputString, passwordEncoder)
        then: "提示密码非法"
        def error = thrown(IllegalArgumentException)
        error.message == errorMessage
        where:
        inputString         || errorMessage
        "bad1.sL"           || "Password must be 8 or more characters in length."
        "bada1.hiLiuweishi" || "Password must be no more than 16 characters in length."
        "daxie1.ishaoshiy"  || "Password must contain 1 or more uppercase characters."
        "daxie.ishSHAhiy"   || "Password must contain 1 or more digit characters."
        "XIAOXIE.HISH1OYI"  || "Password must contain 1 or more lowercase characters."
        "1eshuzifuzsJUUU"   || "Password must contain 1 or more special characters."
        "1esabcdeuzsJUU."   || "Password contains the illegal alphabetical sequence 'abcde'."
        "1es12345uzsJUU."   || "Password contains the illegal numerical sequence '12345'.Password contains the illegal QWERTY sequence '12345'."
        "1esqwertuzsJUU."   || "Password contains the illegal QWERTY sequence 'qwert'."
        "1esqweru zsJUU."   || "Password contains a whitespace character."
    }
}
