/**
 * 值对象是非常重要的DDD部件。它用来度量和描述事物，可以非常容易地使用值对象
 * <p>
 * 来进行创建、测试、使用、优化和维护。
 * <p>
 * 我们应当尽量使用值对象来建模而不是实体对象，即便一个领域概念必须建模成
 * 实体，但在设计时也应该更偏向于将其作为值对象容器，而不是子实体容器。
 * </p>
 * <p>
 * 值对象有以下特征：
 * <ul>
 *      <li>度量或描述了领域中某件的东西的概念，比如一个人的年龄、名字。</li>
 *      <li>
 *          不可变，创建之后便不再改变。
 *          详情参考{@link cn.xialugui.identityaccess.domain.model.user.valueobject.UserId}
 *      </li>
 *      <li>
 *          将不同属性组合成一个概念整体，
 *          详情参考{@link cn.xialugui.identityaccess.domain.model.user.valueobject.Username}
 *      </li>
 *      <li>
 *          当其发生改变时，可以用另一个值对象予以替换，
 *          详情参考{@link cn.xialugui.identityaccess.domain.model.user.valueobject.Nickname}
 *      </li>
 *      <li>
 *          可以比较，
 *          详情参考{@link cn.xialugui.identityaccess.domain.model.user.valueobject.Username#equalsTo(cn.xialugui.identityaccess.domain.model.user.valueobject.Username other)}
 *      </li>
 *      <li>
 *          不会对协作对象造成副作用，
 *          详情参考{@link cn.xialugui.identityaccess.domain.model.user.valueobject.Username#with号(java.lang.String 号)}
 *      </li>
 * </ul>
 * <p>
 * 值对象的最小化集成：
 * <p>
 * 此刻我们有两个限界上下文：文件协同和身份与访问。文件协同中的字段{@code creator}是身份与访问中User的id字段，
 * 我们如何将其从身份与访问限界上下文中流转到文件协同上下文？正确的方法是文件协同上下文通过防腐层查询获得，身份与访问
 * 通过值对象流转。可参考文件协同限界上下文的{@code Creator}值对象。
 * <p>
 * 用值对象表示标准类型：
 * <p>
 * 通常系统中都会存在标准类型，它是表示事物类型的描述对象。比如，系统定义了邮箱Email值对象，
 * 与此同时，Email有不同厂商：网易、腾讯、阿里或者百度。此时我们可以使用标准类型NETEASE、TENCENT、ALIBABA和BAIDU。
 * 通常，为了维护方便，我们会为标准类型创建单独的限界上下文。在上下文中，类型就是实体，有持久化周期。其它限界上下文使用其
 * 最小化集成。
 * <p>
 * 详情参考{@link cn.xialugui.identityaccess.domain.model.user.valueobject.EmailType}
 * <p>
 * 测试值对象：
 * <p>
 * 值对象的测试详见{@code test/groovy/cn.xialugui.identityaccess.domain.model.user.valueobject.UsernameSpecification}
 * <p>
 * 持久化值对象：
 * <p>
 * 对象-关系映射{@code （ORM，比如Hibernate）}持久化机制是流行的。但是，使用
 * {@code ORM}将每个类映射到一张数据库表，再将每个属性映射到数据库表中的列会增
 * 加程序的复杂性。当下，NoSQL数据库和键值对存储越来越受到人们的欢迎，因
 * 为它们具有高性能、可伸缩性、容错性和高可用性等优点。键值对存储可以在很
 * 大程度上简化对聚合的持久化。在我们的例子中，我们依然使用ORM持久化机制。
 * <ul>
 *     <li>
 *         拒绝由数据建模泄漏带来的不利影响：
 *         在可能的情况下， 尽量根据领域模型来设计数据模型，而不是根据数据模型
 *         来设计领域模型。采用前者，我们是在从领域模型的角度看待问题。而采用后者，
 *         我们则是从持久化的角度看待问题，此时的领域模型只是对数据模型的映射而
 *         已。采用面向领域模型的思维方式——DDD思维方式——而不是数据模型思维
 *         方式，这样我们可以免除由数据模型泄漏所造成的影响。
 *     </li>
 *     <li>
 *         未完待续...
 *     </li>
 * </ul>
 *
 * @author 夏露桂
 * @since 2021/7/23 18:14
 */
package cn.xialugui.identityaccess.domain.model.user.valueobject;