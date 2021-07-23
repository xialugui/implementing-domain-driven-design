package cn.xialugui.identityaccess.domain.model.user.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 手机号
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
public final class MobilePhone extends ValueObject<MobilePhone> {
    private String mobilePhone;

    public MobilePhone(String mobilePhone) {
        setMobilePhone(mobilePhone);
    }

    @Override
    protected boolean equalsTo(MobilePhone other) {
        return false;
    }
}
