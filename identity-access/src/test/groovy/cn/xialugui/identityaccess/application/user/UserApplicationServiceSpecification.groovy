package cn.xialugui.identityaccess.application.user

import cn.xialugui.identityaccess.domain.model.user.RegisterService
import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

/**
 *
 * @author 夏露桂* @since 2021/8/18 17:13
 */
@Title("用户应用服务单元测试")
@Subject(UserApplicationService)
class UserApplicationServiceSpecification extends Specification {
    RegisterService registerService = Mock()
    UserRepository repository = Mock()
    UserApplicationService applicationService

    def setup() {
        applicationService = new UserApplicationService(
                repository: repository,
                registerService: registerService
        )
    }

/*    def '注册时用户名，邮箱，电话不能存在重复，重复是指除已注销用户外，都不能重复'() {
        when:'注册用户'
        applicationService.register(new CreateCommand(

        ))
    }*/
}
