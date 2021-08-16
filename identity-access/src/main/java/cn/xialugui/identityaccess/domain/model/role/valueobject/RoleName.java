package cn.xialugui.identityaccess.domain.model.role.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

/**
 * 角色名
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Valid
public final class RoleName extends ValueObject<RoleName> {
    private String name;

    public RoleName(String name) {
        setName(name);
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    protected boolean equalsTo(RoleName other) {
        return false;
    }
}
