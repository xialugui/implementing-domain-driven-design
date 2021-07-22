/**
 * 该模块是用于定义领域中的模块，所以命名为{@code domain}，这种命名
 * 与分层架构和六边形架构（洋葱架构）是兼容的。分层系统也会用到六边形架构和依赖注入。
 * 六边形架构中，应用程序（{@code Application}）包含了领域模型。{@code domain}
 * 可能只做为低层模块的容器而不包含具体的类／接口，也就是说它只包含文件夹而不包含类／接口文件。
 *
 * <p>下层参考{@link cn.xialugui.identityaccess.domain.model}
 *
 * @author 夏露桂
 * @since 2021/7/22 17:29
 */
package cn.xialugui.identityaccess.domain;