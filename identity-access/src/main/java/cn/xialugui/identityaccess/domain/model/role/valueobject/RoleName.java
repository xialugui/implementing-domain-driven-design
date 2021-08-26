package cn.xialugui.identityaccess.domain.model.role.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 角色名
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public final class RoleName extends ValueObject<RoleName> {
    /**
     * 角色名2-16位数字、字符和中文
     */
    @Pattern(regexp = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]{2,16}$")
    @NotNull
    private String name;

    public RoleName(String name) {
        setName(name);
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    protected boolean equalsTo(RoleName other) {
        return getName().equals(other.getName());
    }
}
