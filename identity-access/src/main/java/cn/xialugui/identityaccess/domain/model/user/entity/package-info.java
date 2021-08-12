/**
 * 实体
 * <p>
 * 唯一的身份标识和可变性特征将实体对象和值对象区分开来。
 * 在设计实体时，我们首先需要考虑实体的本质特征，特别是实体的唯一标识
 * 和对实体的查找，而不是一开始便关注实体的属性和行为。只有在对实体的本质
 * 特征有用的情况下，才加入相应的属性和行为。值对象可以用于存放实体的唯一标识。
 * 值对象是不变的，这可以保证实体身份的稳定性，并且与身份标识相关的行为也可以得到集中处理。
 * </p>
 * <p>
 * 身份标识生成策略有以下几种：
 *     <ul>
 *         <li>
 *             用户提供一个或多个初始唯一值作为程序输入，程序应该保证这些初始值是唯一的。
 *             <p>
 *              让用户手动输入对象标识看起来很简单，但有时会变得很复杂，如用户拼写错误这类情况
 *              常有发生。所以不建议使用此方法。
 *             </p>
 *         </li>
 *         <li>
 *             程序内部通过某种算法自动生成身份标识，此时可以使用一些类库或框架，
 *             当然程序自身也可以完成这样的功能。参考{@link java.util.UUID}
 *             <p>
 *                 {@link java.util.UUID}这种方式是快速生成唯一标识的方法，不需要和持久化机制交互。{@link cn.xialugui.identityaccess.domain.model.user.valueobject.UserId}
 *                 就是使用这种方法。{@link java.util.UUID}在必要情况下可以截取一部分来作为唯一标识，
 *                 比如我们可以使用时间和{@link java.util.UUID}的某一小部分拼接唯一标识。这样做满足了可读性、全局唯一性。<br>
 *                另外，我们还可以向标识加入其它信息，比如创建时间，参考{@link cn.xialugui.identityaccess.domain.model.user.valueobject.UserId}。
 *                一些第三方框架比如Apache Commons提供了<a href="https://commons.apache.org/sandbox/commons-id/">Commons Id</a>组件，该组件提供了5种标识生成器。
 *                  <br>
 *                  对于聚合根的唯一标识， 我们还可以采用资源库来生成唯一标识∶{@link cn.xialugui.identityaccess.domain.model.user.repository.UserRepository#nextIdentity()}
 *             </p>
 *         </li>
 *         <li>
 *             程序依赖于持久化存储，比如数据库，来生成唯一标识。参考{@link javax.persistence.GeneratedValue}<br>
 *             但从数据库中获取标识比直接从应用程序中生成标识要慢地多，利用Hibernate的{@code MySQL}的自增序列可以提升性能，同时
 *             也简单，不过时间确实一个大问题。
 *
 *         </li>
 *         <li>
 *             另一个限界上下文（系统或程序）已经决定出了唯一标识，这作为程序的输入，用户可以在一组标识中进行选择。如文件协同
 *             限界上下文接收身份和访问限界上下文中用户id作为文件创建者。同时外部实体可能会引入其它属性，这会出现对象同步的问题，
 *             参考
 *             {@code file-collaboration/src/main/java/cn/xialugui/filecollaboration/domain/model/file/valueobject/Creator.java}
 *
 *         </li>
 *         <li>
 *             标识生成时间，实体唯一标识的生成既可以发生在对象创建的时候，也可以发生在持久化对
 *              象的时候。有时我们需要及早地生成实体标识，而有时标识生成时间则不那么重
 *              要。在需要考虑标识生成时间时，我们应该知道需要将哪些因素考虑在内。
 *              <br>
 *              有时我们在实体生成之前并不需要实体标识，这时我们可以将标识生成的任务委托给资源库下的数据存储设施。这是最简单的一种方式。
 *              但是另一种情况下，这种方式变得很糟糕，当我们需要在聚合初始化完成之后向外部系统发送领域事件时，在聚合标识生成之前领域事件已经
 *              被接收了，此时领域事件中却不包含聚合标识，为了使这个流程正常化，我们必须先生成唯一标识。先生成唯一标识的方法在上面有介绍，这里不再
 *              赘述。还有一个问题，在两个或者多个实体被加入{@link java.util.HashSet}时，由于没有唯一标识，所以它们被认为是相等的，
 *              这会造成严重的bug且难以发现。
 *         </li>
 *         <li>
 *             委派标识。有些ORM工具，比如Hibernate，通过自己的方式来处理对象的身份标识。Hibernate更倾向于使用数据库提供的机制，
 *             比如使用一个数值序列来生成实体标识。如果我们自己的领域需要另外一种实体标识，此时这两者将产生冲突。为了解决这个问题，
 *             我们需要使用两种标识，一种为领域所使用，一种为ORM所使用，在Hibernate中，这被称为委派标识。委派标识通常是long或者int
 *             类型的，其对应数据库中的id列，且拥有主键约束。委派标识和领域中的实体标识没有任何关系，委派标识只是为了迎合ORM。此外我们还需要将
 *             委派标识隐藏起来避免造成持久化泄漏。参考{@link cn.xialugui.identityaccess.domain.model.IdentifiedDomainObject}
 *         </li>
 *         <li>
 *             标识稳定性。在多数情况下，我们都不应该修改实体的唯一标识， 这样可以在实体的整个生命周期中保持标识的稳定性。
 *             保持标识稳定性的方法参考{@link cn.xialugui.identityaccess.domain.model.user.aggregate.User#setUserId(cn.xialugui.identityaccess.domain.model.user.valueobject.UserId)}
 *              测试用例参考{@code identity-access/src/test/groovy/cn/xialugui/identityaccess/domain/model/user/aggregate/UserSpecification.groovy}
 *         </li>
 *     </ul>
 * </p>
 * <br>
 * <p>
 *     发现实体及其本质特征
 *     <ul>
 *         <li>
 *              创建实体，创建实体时，我们有必要保持其构造函数参数的非空性，为了实现这种机制，我们必须对参数进行验证。参考{@link cn.xialugui.identityaccess.domain.model.user.aggregate.User#User(cn.xialugui.identityaccess.domain.model.user.valueobject.UserId, cn.xialugui.identityaccess.domain.model.user.valueobject.Username, cn.xialugui.identityaccess.domain.model.user.valueobject.Nickname, cn.xialugui.identityaccess.domain.model.user.valueobject.Email, cn.xialugui.identityaccess.domain.model.user.valueobject.MobilePhone, cn.xialugui.identityaccess.domain.model.user.valueobject.Password)}
 *         </li>
 *         <li>
 *             验证，验证的主要目的在于检查模型的正确性，检查的对象可以是某个属性，也可以是整个对象，甚至是多个对象的组合。
 *             验证有三个级别：属性，整体，组合对象。属性验证参考{@link cn.xialugui.identityaccess.domain.model.user.aggregate.User#setUserId(cn.xialugui.identityaccess.domain.model.user.valueobject.UserId)}
 *            、{@link cn.xialugui.identityaccess.domain.model.user.aggregate.User#setNickname(cn.xialugui.identityaccess.domain.model.user.valueobject.Nickname)}；
 *              在单个实体验证通过的情况下， 要再对由不同实体组成的整体对象或组合对象进行验证，也将变得更加简单。整体验证参考{@link cn.xialugui.identityaccess.domain.model.user.aggregate.User#validate(cn.xialugui.identityaccess.domain.ValidationNotificationHandler)}；
 *              验证组合对象暂无。//todo
 *         </li>
 *         <li>
 *             跟踪变化，有时领域专家可能会关心发生在模型中的一些重要事件，此时我们便应该对实体的一些特殊变化进行跟踪了。
 *             跟踪变化最实用的方法是领域事件和事件存储。我们为领域专家所关心的
 *             所有状态改变都创建单独的事件类型，事件的名字和属性表明发生了什么样的事
 *             件。当命令操作执行完后，系统发出这些领域事件。事件的订阅方可以接收发生在
 *             模型上的所有事件。在接收到事件后，订阅方将事件保存在事件存储中。领域专家并不会关心发生在模型中的所有变化，
 *             但这却是技术团队所应该关心的。这主要是出于技术上的原因，请参考事件源模式。//todo
 *         </li>
 *     </ul>
 * </p>
 *
 * @author 夏露桂
 * @since 2021/8/11 12:01
 */
package cn.xialugui.identityaccess.domain.model.user.entity;