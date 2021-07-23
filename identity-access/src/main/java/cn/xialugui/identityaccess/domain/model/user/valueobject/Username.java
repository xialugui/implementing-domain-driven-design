package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户名
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
public final class Username extends ValueObject<Username> {
    private String username;

    public Username(String username) {
        setUsername(username);
    }

    @Override
    protected boolean equalsTo(Username other) {
        return StringUtils.equals(username, other.getUsername());
    }
}
