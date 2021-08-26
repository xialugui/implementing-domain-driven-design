package cn.xialugui.identityaccess.domain.model.role.valueobject;

import cn.xialugui.identityaccess.domain.model.Identifier;
import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author 夏露桂
 * @since 2021/7/23 16:01
 */
@Embeddable
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class RoleId extends ValueObject<RoleId> implements Identifier {
    private static final char[] chars = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    };
    @Pattern(regexp = "^[0-9a-zA-Z]{2,16}$")
    @Column(unique = true)
    @NotNull
    private String roleId;

    public RoleId(String roleId) {
        setRoleId(roleId);
    }

    public static RoleId random() {
        StringBuilder result = new StringBuilder();
        int max = chars.length;
        for (int i = 0; i < 16; i++) {
            result.append(chars[(int) (Math.random() * max)]);
        }
        return new RoleId(result.toString());
    }

    @Override
    public String toString() {
        return this.roleId;
    }

    @Override
    protected boolean equalsTo(RoleId other) {
        return StringUtils.equals(getRoleId(), other.getRoleId());
    }
}

