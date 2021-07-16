package cn.xialugui.user.domain.model.aggregate;

import cn.xialugui.user.domain.model.command.CreateCommand;
import cn.xialugui.user.domain.model.entity.Role;
import cn.xialugui.user.domain.model.valueobject.*;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.List;

/**
 * 用户聚合根
 *
 * @author 夏露桂
 * @since 2021/7/14 17:50
 */
@Getter
public final class User extends AbstractAggregateRoot<User> {
    private Username username;
    private NickName nickname;
    private Email email;
    private MobilePhone mobilePhone;
    private Password password;
    private List<Role> roles;

    public User(CreateCommand command) {
        this.password = new Password(command.getPassword());
    }
}

