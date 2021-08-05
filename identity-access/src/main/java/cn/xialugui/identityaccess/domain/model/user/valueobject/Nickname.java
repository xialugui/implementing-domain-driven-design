package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * 昵称
 * 值对象的可替换性指的是用户昵称变更时，采用的不是
 * <p>
 * {@code
 * nickName.setNickname("xxx");
 * }
 * <p>
 * 而是
 * <p>
 * {@code
 * nickName = new Nickname("xxx");
 * }
 * 这里，{@code setNickname()}的访问修饰符是{@code private}，外部是
 * 不能直接访问的。这种方式和整形类似。
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public final class Nickname extends ValueObject<Nickname> {
    private String nickname;

    public Nickname(String nickname) {
        setNickname(nickname);
    }


    @Override
    protected boolean equalsTo(Nickname other) {
        return false;
    }
}
