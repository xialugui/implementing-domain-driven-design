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
 *                另外，我们还可以向标识加入其它信息，比如创建时间，参考{@link cn.xialugui.identityaccess.domain.model.user.valueobject.UserId}
 *
 *             </p>
 *         </li>
 *         <li>
 *             程序依赖于持久化存储，比如数据库，来生成唯一标识。参考{@link javax.persistence.GeneratedValue}
 *         </li>
 *         <li>
 *             另一个限界上下文（系统或程序）已经决定出了唯一标识，这作为程序的输入，用户可以在一组标识中进行选择。如文件协同
 *             限界上下文接收身份和访问限界上下文中用户id作为文件创建者。
 *         </li>
 *     </ul>
 * </p>
 *
 * @author 夏露桂
 * @since 2021/8/11 12:01
 */
package cn.xialugui.identityaccess.domain.model.user.entity;