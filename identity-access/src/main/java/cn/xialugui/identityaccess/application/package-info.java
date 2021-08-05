/**
 * 应用层
 * 应用层可以不止一个模块，比如用户对应一个模块，角色对应一个模块：
 * <p>
 * cn.xialugui.identityaccess.application.user
 * <br>
 * cn.xialugui.identityaccess.application.role
 * </p>
 * 和REST资源规则一样，只有在需要的时候才为应用层划分子模块，一开始我们只需要在
 * <p>
 * cn.xialugui.identityaccess.application
 * </p>
 * 下进行开发。
 * <ul>
 *     <li>
 *         应用服务，参考{@link cn.xialugui.identityaccess.application.user.UserService}
 *     </li>
 *     <li>
 *         组合多个限界上下文，暂无
 *     </li>
 *     <li>
 *         基础设施，参考{@link cn.xialugui.identityaccess.domain.model.user.repository.UserRepository}的父类。
 *         父类基于Spring Boot动态代理生成具体实现类。一般来讲，其实现类应该放置在{@code infrastructure/persistence}。
 *         Spring Boot替我们实现了这个功能，在需要的情况下，我们依然可以自定义实现类。
 *     </li>
 *     <li>
 *         企业组件容器，我们使用的Spring，Spring可以参考<a href="https://spring.io/">Spring官网</a>
 *     </li>
 * </ul>
 *
 * @author 夏露桂
 * @since 2021/7/23 11:25
 */
package cn.xialugui.identityaccess.application;