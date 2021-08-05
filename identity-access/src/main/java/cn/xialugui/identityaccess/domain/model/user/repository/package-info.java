/**
 * 资源库
 * <p>
 * 我们将聚合存放在资源库中，之后再通过该资源库来获取相同的实例。如果
 * 你修改了某个聚合，那么这种改变将被资源库所持久化。如果你从资源库中移除了
 * 某个实例，那么从那以后你将无法重新获取该实例。
 * </p>
 * <p>
 * 这些像集合一样的对象都是和持久化相关的。每一种聚合类型都将拥有一个
 * 资源库。通常来说，聚合类型和资源库之间存在着一对一的关系。
 * </p>
 * <p>
 * 严格来讲，只有聚合才拥有资源库。
 * </p>
 * <p>
 * 存在两种类型的资源库设计，即面向集合
 * {@code collection-oriented}
 * 的设计（参考{@link cn.xialugui.identityaccess.domain.model.user.repository.UserCollection}）
 * 和
 * 面向持久化{@code persistence-oriented}的设计（参考{@link cn.xialugui.identityaccess.domain.model.user.repository.UserRepository}）。
 * </p>
 * <br>
 * <p>
 *  <ul>
 *      <li>
 *          额外行为，参考{@link cn.xialugui.identityaccess.domain.model.user.repository.UserRepository#size()}和
 *          {@link cn.xialugui.identityaccess.domain.model.user.repository.UserRepository#findUsernameByUserId(cn.xialugui.identityaccess.domain.model.user.valueobject.UserId)}
 *      </li>
 *      <li>
 *          管理事务，通常来说，我们将事务放在应用层。参考{@link cn.xialugui.identityaccess.application.user.UserService#doTransaction()}
 *      </li>
 *      <li>
 *          类型层级，待定。
 *      </li>
 *      <li>
 *          资源库vs数据访问对象{@code DAO}。资源库和{@code DAO}是不同的。一个{@code DAO}主要从数据库表的角度来看待问题，并且
 *          提供{@code CRUD}操作。与{@code DAO}相关的模式通常只是对数据库表的一层封装。而另一方面，
 *          资源库和数据映射器{@code Data Mapper}（参考{@link cn.xialugui.identityaccess.domain.model.user.repository.UserRepository#findUsernameByUserId(cn.xialugui.identityaccess.domain.model.user.valueobject.UserId)}）则更加偏向于对象，因此通常被用于领域模型中。
 *          通常来说，你可以将资源库当作DAO来看待。但是请注意一点，在设计资源库
 *          时，我们应该采用面向集合的方式，而不是面向数据访问的方式。这有助于你将自
 *          己的领域当作模型来看待，而不是CRUD操作。
 *      </li>
 *      <li>
 *          测试资源库，参考{@link test/groovy/cn.xialugui.identityaccess.domain.model.user.repository.UserRepositorySpecification}
 *      </li>
 *  </ul>
 * </p>
 *
 * @author 夏露桂
 * @since 2021/8/5 14:59
 */
package cn.xialugui.identityaccess.domain.model.user.repository;