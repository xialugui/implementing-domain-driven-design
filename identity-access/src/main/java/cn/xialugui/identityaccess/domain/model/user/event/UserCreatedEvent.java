package cn.xialugui.identityaccess.domain.model.user.event;

import cn.xialugui.identityaccess.domain.model.AbstractDomainEvent;
import cn.xialugui.identityaccess.domain.model.user.valueobject.UserId;
import cn.xialugui.identityaccess.domain.model.user.valueobject.Username;

/**
 * 用户创建事件
 * <p>
 * 显然用户创建应该包含其{@link #userId}，其它属性可依据需求增加。
 * 领域事件通常是不可变的，所以只允许其构造函数书初始化所有状态，且仅提供
 * 属性方法方法。
 * </p>
 *
 * <ul>
 *      <li>
 *          创建具有聚合特征的领域事件，此小结考虑的是领域事件的聚合特性，先不做介绍。
 *      </li>
 *      <li>
 *          身份标识，主要是如何区分事件的唯一性，避免重复处理。这部分可由基础设施，例如消息中间件和JMS
 *          支持。
 *      </li>
 * </ul>
 *
 * @author 夏露桂
 * @since 2021/8/17 11:11
 */
public final class UserCreatedEvent extends AbstractDomainEvent {
    private final UserId userId;
    private final Username username;

    public UserCreatedEvent(UserId userId, Username username) {
        super();
        this.userId = userId;
        this.username = username;
    }

    public UserId userId() {
        return this.userId;
    }

    public Username username() {
        return this.username;
    }
}
