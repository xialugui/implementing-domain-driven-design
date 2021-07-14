package cn.xialugui.user.domain.aggregate;

import cn.xialugui.user.domain.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;

/**
 * 用户聚合根
 *
 * @author 夏露桂
 * @since 2021/7/14 17:50
 */
@Getter
@Setter
public final class User extends AbstractAggregateRoot<User> {
    private String username;
    private String nickname;
    private String email;
    private String mobilePhone;
    private List<Role> roles;
}

