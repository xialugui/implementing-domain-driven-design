/**
 * 领域事件
 * <p>
 * 本地限界上下文中的其他聚合实例可以通过领域事件的方式予以同步。
 * 领域事件还可以用于使远程依赖系统与本地系统保持一致。
 * 本地系统和远程系统的解耦有助于提高双方协作服务的可伸缩性。
 * </p>
 * <br>
 * <p>
 * 所有的领域事件都将实现{@link cn.xialugui.identityaccess.domain.model.DomainEvent}接口，
 * 该接口定义了一个{@link cn.xialugui.identityaccess.domain.model.DomainEvent#occurredOn()}方法。
 * 现在，我们为用户注册创建一个事件：{@link }
 * </p>
 * <p>
 *     <ul>
 *         <li>
 *             建模领域事件，参考{@link cn.xialugui.identityaccess.domain.model.user.event.UserCreatedEvent}
 *
 *         </li>
 *         <li>
 *             从领域模型中发布事件，参考{@link cn.xialugui.identityaccess.domain.model.user.aggregate.User#User(cn.xialugui.identityaccess.domain.model.user.valueobject.UserId, cn.xialugui.identityaccess.domain.model.user.valueobject.Username, cn.xialugui.identityaccess.domain.model.user.valueobject.Nickname, cn.xialugui.identityaccess.domain.model.user.valueobject.Email, cn.xialugui.identityaccess.domain.model.user.valueobject.MobilePhone, cn.xialugui.identityaccess.domain.model.user.valueobject.Password)}
 *             ，其中发布方参考{@link cn.xialugui.identityaccess.domain.model.AbstractAggregateRoot#andEvent(java.lang.Object)}
 *             接收方参考{@link cn.xialugui.identityaccess.application.role.RoleDomainEventListener}。此处我们未
 *             使用消息机制。
 *         </li>
 *         <li>
 *             向远程限界上下文发布领域事件，这其中需要使用消息机制，我们将会在后期实现//todo
 *             使用事件驱动的目的是将系统设计成自治的，拥有独立完成各自功能的属性。。
 *             自治服务可以避免对远程过程调用（RPC）的使用，带来更高程度的独立性。事件的传递是
 *             存在时延的，我们在引入事件机制之前评估可接收的时延。对于这些，领域专家是非常清楚的。
 *             大多数情况下都是可接受的。
 *         </li>
 *         <li>
 *             事件存储，事件存储我们在后期的<a href="https://axoniq.io/product-overview/axon-framework">Axon</a>
 *             框架会去实现，这里不做介绍。Axon框架是DDD和CQRS框架，感兴趣的同志可以学习参考。
 *         </li>
 *         <li>
 *             待更新//todo
 *         </li>
 *     </ul>
 *
 * </p>
 *
 * @author 夏露桂
 * @since 2021/8/17 11:03
 */
package cn.xialugui.identityaccess.domain.model.user.event;