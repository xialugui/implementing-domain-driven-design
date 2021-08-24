package cn.xialugui.identityaccess.domain.model.user.valueobject

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

/**
 *
 * @author 夏露桂* @since 2021/8/24 17:09
 */
@Title("用户id单元测试")
@Subject(UserId)
class UserIdSpecification extends Specification {

    @Unroll("用户名：#input 非法")
    def "用户id只能是2-16位数字或字母"() {
        when: "初始化"
        new UserId(input)
        then: "不符合则抛出异常"
        def error = thrown(IllegalArgumentException)
        error.message == "用户id只能是2-16位数字或字母"
        where:
        input << [
                "",
                " ",
                "a",
                "1",
                "12.",
                "12adsfadfasdfad ",
                "12adsfadfasdfadds",
        ]
    }
}
