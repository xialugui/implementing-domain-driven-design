/**
 * 领域服务类存放位置。另一种方式是将领域服务和领域模型同级放置
 * （{@code cn.xialugui.identityaccess.domain.service}），
 * 此时领域服务被看成是位于模型之上的迷你层，或者是环绕模型的一层。
 * 这种方式可能会导致贫血领域模型。
 *
 * <p>在我们的项目中，我们将领域服务放置在最终概念模块中，所以，此目录
 * 只用作示例。
 * <p>
 * 参考 {@link cn.xialugui.identityaccess.domain.model.user.RegisterService}
 * </p>
 *
 * @author 夏露桂
 * @since 2021/7/22 11:58
 */
package cn.xialugui.identityaccess.domain.model.service;