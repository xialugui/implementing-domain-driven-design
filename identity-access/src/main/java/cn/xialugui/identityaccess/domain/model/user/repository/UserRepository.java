package cn.xialugui.identityaccess.domain.model.user.repository;

import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import cn.xialugui.identityaccess.domain.model.user.valueobject.UserId;
import cn.xialugui.identityaccess.domain.model.user.valueobject.Username;
import cn.xialugui.identityaccess.resources.user.UserDetailsProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * 面向持久化设计的资源库
 * <p>
 * 如果持久化机制不支持对对象变化的跟踪，无论是显式的还是隐式的，那么
 * 采用面向集合资源库便不再适用了。此时，我们可以考虑使用面向持久化资源库，
 * 这是一种基于保存操作的资源库。在使用内存数据库或者{@code NoSQL}键值对存
 * 储时，每次新建聚合或修改聚合之后，我们都需要调用资源库中的
 * {@code save()}方法或者与之类似的方法。
 * </p>
 * <br>
 * <p>
 * 即便你所使用的ORM工具提供了对面向集合资源库的支持，我们依然有理由
 * 使用面向持久化的资源库。如果你使用的是面向集合资源库，但之后你决定从关
 * 系型数据库转向键值对存储，你应该怎么办呢?此时你不得不大规模地修改自己
 * 的应用层，在所有更新聚合的地方，你都得改成使用{@code save()}方法。另外，资源库中的
 * {@code add()}和 {@code addAlI()}方法也没用了，因此你可能还会删除掉此两方法。如果你所选用的
 * 持久化机制在将来很有可能进行更换， 那么请设计更加灵活的接口以备将来之需。
 * 这样做的风险在于，你当前的{@code ORM}工具可能会诱使你忘却使用{@code add()}方法，因为该
 * 方法只有在更改了持久化机制之后才是必需的。另一方面，它的好处便是:在将来
 * 你可以非常方便地更换持久化机制。
 * </p>
 * <br>
 * <p>
 * 在向数据存储中添加新建对象或修改既有对象时，我们都必须显式地调用{@code put}方法，
 * 该方法将以新的值来替换先前关联在某个键上的原值。这种类型的数据存储可以极大地简
 * 化对聚合的读写。正因如此，这种数据存储也称为聚合存储{@code Aggregate Store}或面向聚合
 * 数据库{@code Aggregate-Oriented Database}。
 * </p>
 * <br>
 *
 * <p>
 * 对于更新操作，在有必要时我们需要为应用层编写测试。此时，我们可以使用一
 * 个内存资源库来测试每一个保存操作， 也包括更新操作。
 * </p>
 * <br>
 * <p>
 * 我们还可以向资源库接口中添加一些额外的行为。其中之一便是计算聚合实例的总数。你可能会将该行
 * 为方法命名为{@code count()}，但是由于一个资源库应该尽可能地模拟一个集合，所以我们使用
 * {@code size()}。参考{@link UserRepository#size()}
 * </p>
 * <br>
 * <p>
 * 我们还有可能在资源库中创建一些特殊的查找方法。比如，如果我们
 * 需要在用户界面中显示数据，而这些数据来自于多个聚合，此时我们不用先分别
 * 获取到每个聚合，再从中提取出所需数据，而是可以使用
 * <br>
 * 用例优化查询{@code Use Case Optimal Query}
 * <br>
 * 的方法直接查询所需要的数据。此时，我们可以直接在持久化机
 * 制上执行查询，然后将查询结果放在一个值对象中予以返回。参考{@link UserRepository#findUsernameByUserId(UserId)}
 * </p>
 *
 * @author 夏露桂
 * @since 2021/8/5 14:58
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * 查询用户聚合的数量
     *
     * @return 总数
     */
    @Query("select count(distinct userId) from User")
    int size();

    /**
     * JPA推荐返回值使用领域类型接收或者是合法的映射。
     * 这里我们使用简单的值对象做例子，它和映射是等效的。
     *
     * @param userId 用户id
     * @return 用户名
     */
    @Query("select user.username from User as user where user.userId=:#{#userId.userId}")
    Username findUsernameByUserId(UserId userId);

    User findByUsername(Username username);

    Optional<UserDetailsProjection> findByUserId(UserId userId);

    /**
     * 将唯一标识的生成放在资源库中是一种自然的选择。
     *
     * @return 用户id
     */
    default UserId nextIdentity() {
        return new UserId(UUID.randomUUID().toString());
    }
}
