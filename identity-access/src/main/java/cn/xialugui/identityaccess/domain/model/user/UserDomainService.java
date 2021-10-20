package cn.xialugui.identityaccess.domain.model.user;

import cn.xialugui.identityaccess.domain.model.role.aggregate.Role;
import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository;
import cn.xialugui.sharedkernel.infrastructure.constraint.validator.CheckNotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


/**
 * 领域服务，我们将用户的领域服务都几种在此类中。这种方式有好处也有坏处。
 *
 * @author 夏露桂
 * @since 2021/9/10 17:14
 */
@Component
@RequiredArgsConstructor
@Validated
public class UserDomainService {
    public final UserRepository repository;

    @CheckNotNull
    public void addRole(User user, Role role) {

    }
}
