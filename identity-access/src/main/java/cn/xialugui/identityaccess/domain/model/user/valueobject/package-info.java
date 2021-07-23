/**
 * 值对象是非常重要的DDD部件。它用来度量和描述事物，可以非常容易地使用值对象
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
 *      <li>将不同属性组合成一个概念整体</li>
 *      <li>当其发生改变时，可以用另一个值对象予以替换</li>
 *      <li>可以比较</li>
 *      <li>不会对协作对象造成副作用</li>
 * </ul>
 *
 * </p>
 *
 * @author 夏露桂
 * @since 2021/7/23 18:14
 */
package cn.xialugui.identityaccess.domain.model.user.valueobject;