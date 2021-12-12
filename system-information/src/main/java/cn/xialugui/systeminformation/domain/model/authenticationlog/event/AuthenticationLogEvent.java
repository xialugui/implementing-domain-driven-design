package cn.xialugui.systeminformation.domain.model.authenticationlog.event;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 领域事件，显然我们在这里使用了{@link  AuthenticationLogId}这个值对象
 * 来组织领域事件，这种做法很符合直觉，看起来也很方便，但存在几个问题：
 * <ul>
 *     <li>1. 领域对象大部分情况下夹杂了其它非必要信息，而领域事件的原则是只包含最简信息</li>
 *     <li>2. 使用领域对象会将限界上下文的概念泄露到其它限界上下文，反而增加了耦合</li>
 * </ul>
 *
 * @author 夏露桂
 * @since 2021/11/30 20:08
 */
@SuperBuilder
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class AuthenticationLogEvent {
    private final AuthenticationLogId authenticationLogId;
    private final String name;
    private final String ip;
    private final String remarks;
    private final String type;
    private final Long timestamp;
}
