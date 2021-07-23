package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 昵称
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
public final class NickName extends ValueObject<NickName> {
    private String nickname;

    public NickName(String nickname) {
        setNickname(nickname);
    }

    @Override
    protected boolean equalsTo(NickName other) {
        return false;
    }
}
