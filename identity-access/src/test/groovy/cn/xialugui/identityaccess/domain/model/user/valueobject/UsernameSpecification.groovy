package cn.xialugui.identityaccess.domain.model.user.valueobject

import spock.lang.Specification

/**
 * 这里，我们所关心的并不仅仅是单元测试的各个方面，而是演示客户端是如何
 * 使用我们的领域模型的。在设计领域模型时，从客户端的角度思考有助于捕获关
 * 键的领域概念。否则，我们便是在从自己已的角度设计模型，而不是业务的角度。
 *
 * @author 夏露桂* @since 2021/7/27 16:10
 */
class UsernameSpecification extends Specification {
    def Username username;

    def setup() {
        username = new Username(
                "李",
                "白",
                "太白",
                "青莲居士"
        )
    }

    def "值对象的不可变性"() {
        when: "复制"
        Username usernameCopy = new Username(username);
        then: "两者两等"
        username == usernameCopy
        when: "获取全称"
        usernameCopy.fullName()
        then: "依旧相等"
        username == usernameCopy

    }

    def "修改号"() {
        when: "修改号"
        Username usernameChanged = username.with号("白莲")
        then: "已修改"
        usernameChanged.get号() == "白莲"
    }

    def "修改号时参数不能为空"() {
        when: "修改号参数为空"
        Username usernameChanged = username.with号(null)
        then: "已修改"
        thrown(IllegalArgumentException)
    }
}
