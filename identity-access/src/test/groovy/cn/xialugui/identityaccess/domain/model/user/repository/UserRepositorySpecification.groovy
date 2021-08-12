package cn.xialugui.identityaccess.domain.model.user.repository

import cn.xialugui.identityaccess.domain.model.user.aggregate.User
import cn.xialugui.identityaccess.domain.model.user.valueobject.Email
import cn.xialugui.identityaccess.domain.model.user.valueobject.EmailType
import cn.xialugui.identityaccess.domain.model.user.valueobject.UserId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import spock.lang.Specification
import spock.lang.Subject

/**
 * 对于准备一个完整的资源库实现来说，如果存在困难，或者速度太慢，那么我
 * 们可以考虑使用另一种方式。另外，在领域建模早期，你也有可能面临一些麻烦，
 * 比如持久化机制并不可用，或者数据库的Schema还未准备好等。此时，我们便可以
 * 使用一个内存版本的资源库。
 * @author 夏露桂* @since 2021/8/5 16:37
 */
@Subject(UserRepository)
@DataJpaTest
class UserRepositorySpecification extends Specification {
    @Autowired
    UserRepository repository;

    def "添加"() {
        when: '添加一个用户'
        repository.save(new User(
                userId: new UserId("123"),
                email: new Email("1", EmailType.NET_EASE)
        ))
        then: '确实增加了'
        repository.count() == 1
    }
}
