/**
 * 该层定义领域模型，包含接口类和抽象类，例如抽象{@code Entity，ValueObject}。
 * 有争议的是领域服务的位置，可以选择将领域服务放在此层中，也可以放在平行的{@code service}
 * 中：
 *
 * <p>{@code cn.xialugui.identityaccess.domain.service}。
 *
 * <p>如果将模型和领域服务放在分离的包中，那么可以将所有模型模块直接放在{@code domain}下：
 *
 * <p>{@code cn.xialugui.identityaccess.domain.conceptname}
 * <p>这种方式消除了多余的一层，但如果之后你又想将领域服务放在{@code domain.service}中，那将造成尴尬的局面。
 * 不采取这种方式的更重要原因是：我们并不是开发一个领域，而是领域中的模型。所以在命名模型中的最终模块时，使用{@code domain.model}
 * 是最合适的。
 * <p>模型模块按其概念分类以{@code cn.xialugui.identityaccess.domain.model.conceptname}规则命名。
 * 此种方式提供了将领域服务模块从模型分离出的可能，所以最终我们选择上述的模块命名方式。
 *
 *
 * <p>参考{@link cn.xialugui.identityaccess.domain.model.service}
 *
 * @author 夏露桂
 * @since 2021/7/22 17:44
 */
package cn.xialugui.identityaccess.domain.model;