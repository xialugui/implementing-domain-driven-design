package cn.xialugui.identityaccess.domain.model.user.aggregate

import cn.xialugui.identityaccess.application.user.CreateCommand
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title
import spock.lang.Unroll

/**
 *
 * @author 夏露桂* @since 2021/7/15 18:03
 */
@Title("用户聚合根的单元测试")
@Subject(User)
class UserSpecification extends Specification {


    @Unroll('密码：#password，结果：#result')
    def '创建用户时，密码必须合规'() {
        when: '创建用户'
        User user = new User(new CreateCommand(
                password: password
        ))
        then: '不合规则抛出异常'
        thrown(result)

        where: '用例'
        password    || result
        12          || IllegalArgumentException
        12123123123 || IllegalArgumentException
    }


}
