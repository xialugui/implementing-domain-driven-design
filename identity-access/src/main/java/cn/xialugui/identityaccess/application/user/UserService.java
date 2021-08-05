package cn.xialugui.identityaccess.application.user;

import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository;
import cn.xialugui.identityaccess.domain.model.user.valueobject.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * 应用服务
 * 应用服务是领域模型的直接客户。负责用例流的任务协调，每个用例流对应了一
 * 个服务方法。如用户注册用例对应了register这个服务方法。在使用ACID数据库时，
 * 应用服务还负责控制事务以确保对模型修改的原子提交。
 * <p>
 * 应用服务与领域服务是不同的。我们应该将所有的业务领域逻辑放在领域模型中，不
 * 管是聚合、值对象或者领域服务;而将应用服务做成很薄的一层，并且只使用它们
 * 来协调对模型的任务操作。
 * <p>
 * 在此处我们将应用服务的接口和实现定义在了同一个类里，而不是使用独立接口，
 * 因为它并没有多少好处。
 * </p>
 * <br>
 *
 * @author 夏露桂
 * @since 2021/8/5 11:00
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * 注册
     * <p>
     * 注册方法中的签名是领域对象，这意味着用户界面需要
     * 知道这些类型且依赖它们。应用服务被设计成将用户界面完全地
     * 隔离于领域模型。此时，应用服务中的方法签名中将只出现原始类型
     * <p>
     * {@code int、long、double和String }
     * <p>
     * 类型，有可能还有{@code DTO}。但是，更好的方法是使用
     * <p>
     * {@code 命令对象}，见@{@link #register(CreateCommand)}
     * </p>
     * 这其中的选择取决于你的喜好。
     * <p>
     * 不使用模型可以避免依赖和耦合，但失去了强类型检查和基本验证。
     * </p>
     *
     * @param username    用户名
     * @param nickName    昵称
     * @param password    密码
     * @param mobilePhone 手机号
     * @param email       邮箱
     */
    @Transactional
    public void register(
            Username username,
            Nickname nickName,
            Password password,
            MobilePhone mobilePhone,
            Email email
    ) {

    }

    /**
     * 命令对象将一个请求封装到一个对象中，从而使得我们对客户端进行参数化，
     * 包括不同的请求、队列或者日志请求等。我们可以将命令对象看成是序列化
     * 的方法调用。参考{@link CreateCommand}
     *
     * @param createCommand 创建命令
     */
    @Transactional
    public void register(CreateCommand createCommand) {

    }

    /**
     * 基于注解的事务管理
     * <br>
     * 业务方法对用例所需操作进行协调。业务方法会开始一个事务。同，
     * 时，该业务方法将作为领域模型的客户端而存在。在这个过程中，
     * 如果发生错误/异常，那么业务方法将对事务进行回滚。
     */
    @Transactional
    public void doTransaction() {

    }
}
