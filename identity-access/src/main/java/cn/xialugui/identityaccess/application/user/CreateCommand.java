package cn.xialugui.identityaccess.application.user;

import cn.xialugui.identityaccess.domain.model.user.valueobject.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 创建命令
 * <p>
 * 这里没有使用领域对象，而是一些基本类型。其原因可以参考{@link UserService#register(Username, Nickname, Password, MobilePhone, Email)}
 * </p>
 * 我们可以将命令对象看作是DTO，但是它所能表达的比DTO要多。最突出就是以其操作来命名命令对象，
 * 所以其目的和意图更加明显。我们可以将命令对象用作应用服务方法的签名参数，详情见{@link UserService#register(CreateCommand)}
 * <p>
 * 我们还可以将命令对象发送到一个队列，然后分发给命令处理器。命令处理器等同于
 * 应用服务的方法，这样做的好处是可以临时解耦。这种方式可以获得更大的吞吐量，且可以增加
 * 命令处理的伸缩性。
 * </p>
 *
 * @author 夏露桂
 * @since 2021/7/16 15:48
 */
@Getter
@Setter
public class CreateCommand {
    private String 姓;
    private String 名;
    private String 字;
    private String 号;
    private String nickname;
    private String password;
    private String mobilePhone;
    private String email;
}
