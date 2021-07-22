/**
 * 该层定义领域模型，包含接口类和抽象类，例如抽象{@code Entity，ValueObject}。
 * 有争议的是领域服务的位置，可以选择将领域服务放在此层中，也可以放在平行的{@code service}
 * 中{@code cn.xialugui.identityaccess.domain.service},在这里，我们没有选择这种方式，
 * 而是将其放在{@code model}中。领域模型按其概念分类以{@code cn.xialugui.identityaccess
 * .domain.model.conceptname}规则命名。此种方式提供了将领域服务模块从模型分离出的可能。所以
 * 最终我们选择上述的模块命名方式。
 * <p>参考{@link cn.xialugui.identityaccess.domain.model.service}
 *
 * @author 夏露桂
 * @since 2021/7/22 17:44
 */
package cn.xialugui.identityaccess.domain.model;