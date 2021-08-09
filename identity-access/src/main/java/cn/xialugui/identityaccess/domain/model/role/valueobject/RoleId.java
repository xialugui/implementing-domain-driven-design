package cn.xialugui.identityaccess.domain.model.role.valueobject;

import cn.xialugui.identityaccess.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Embeddable;

/**
 * @author 夏露桂
 * @since 2021/7/23 16:01
 */
@Embeddable
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class RoleId extends ValueObject<RoleId> {
    private String id;

    public RoleId(String id) {
        setId(id);
    }

    @Override
    protected boolean equalsTo(RoleId other) {
        return StringUtils.equals(getId(), other.getId());
    }
}

