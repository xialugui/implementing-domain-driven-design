package cn.xialugui.identityaccess.domain.model.user;

import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository;
import cn.xialugui.identityaccess.domain.model.user.valueobject.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 领域服务
 * <p>
 * 领域服务和应用服务是有区别的，在应用服务中，我们不会处理业务逻辑。
 * 而领域是处理业务逻辑的。应用服务是领域模型的客户端，也是领域服务的客户端。
 * 下面一些特性可以帮助区分实体、值对象和领域服务：
 * <ul>
 *     <li>
 *         执行一个显著的业务操作过程。
 *     </li>
 *     <li>
 *         对领域对象进行转换。
 *     </li>
 *     <li>
 *         以多个领域对象作为输入进行计算，结果产生一个值对象。
 *     </li>
 * </ul>
 * </p>
 * <p>
 *     领域服务的过度使用会导致贫血模型，所有的业务逻辑都位于领域服务中，而不是实体和值对象。
 * </p>
 * <p>
 *     你可能会将领域服务声明为一个单独的接口，然后根据依赖倒置原则去实现它。但是在明确知道只有
 *     一种实现类的情况下这样做是没有必要的。所有在这里我们将其直接实现。
 * </p>
 * <p>
 *     常见的命名实现类的方法便是给接口名加上Impl后缀。按照这种方法.我们的认证实现类为RegisterServicelmpl。
 *     此外，实现类和接口通常被放在相同的包下。如果你采用这种方式来命名实现类， 这往往意味着你根本就不需要一个独立接口。
 *     如果领域服务具有多个实现类.那么我们应该根据各种实现类的特点进行命名. 而这往往又意味着在你的领域中存在一些特定的行为功能。
 *     有人认为采用相似的名字来命名接口和实现类有助于代码浏览和定位。但是.还有人则认为将接口和实现类放在相同的包中会使包变得很大.
 *     这是一种糟糕的模块设计. 因此他们偏向于将接口和实现类放在不同的包中，.我们在依赖倒置原则便是这么做的。
 *     比如，可以将接口{@code EncryptionService}放在领域模型中，而将{@code MD5EncryptionService}放在基础设施层中。
 *     这是一个具有争议性的话题，我也知道有很大一部分人依然采用lmpl后缀的方式来命名实现类。即便如此.我们仍然有强烈的理由不这么做。
 *     当然，选择权在你自己手上。有时，领域服务总是和领域密切相关，并且不会有技术性的实现，或者不会有多个实现，
 *     此时采用独立接口便只是一个风格上的问题。与依赖注入相比，我们更倾向于将领域服务作为构造函数参数或者方法参数传入，因为
 *     这样的代码拥有很好的可测试性，甚至比依赖注入更加简单。参考下面{@link #userRepository}的注入方式。
 * </p>
 *
 * <p>
 *     在必要情况下，我们可以对领域服务进行测试。参考{@code groovy/cn/xialugui/identityaccess/domain/model/user/RegisterServiceSpecification.groovy}
 * </p>
 *
 * @author 夏露桂
 * @since 2021/8/9 17:50
 */
@Component
@RequiredArgsConstructor
public class RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void register(
            UserId userId,
            Username username,
            Nickname nickName,
            String password,
            MobilePhone mobilePhone,
            Email email
    ) {

        userRepository.findUserDetailsById(userId).ifPresent(
                userDetailsProjection -> {
                    throw new IllegalArgumentException("用户id" + userDetailsProjection.getUserId() + "已被注册");
                }
        );

        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    throw new IllegalArgumentException("用户" + user.getUsername() + "已被注册");
                });
        Optional.ofNullable(email).flatMap(e -> userRepository.findByEmail(email))
                .ifPresent(user -> {
                    throw new IllegalArgumentException("邮箱" + user.getEmail() + "已被注册");
                });

        userRepository.findByMobilePhone(mobilePhone)
                .ifPresent(user -> {
                    throw new IllegalArgumentException("手机号" + user.getMobilePhone() + "已被注册");
                });
        User user = new User(
                userId,
                username,
                nickName,
                email,
                mobilePhone,
                Password.encodeOf(password, passwordEncoder)
        );
        userRepository.save(user);
    }

    public void registerByMobilePhone(MobilePhone mobilePhone) {
        register(
                UserId.random(),
                Username.random(),
                new Nickname(mobilePhone.getMobilePhone()),
                Password.DEFAULT,
                mobilePhone,
                null
        );
    }
}
