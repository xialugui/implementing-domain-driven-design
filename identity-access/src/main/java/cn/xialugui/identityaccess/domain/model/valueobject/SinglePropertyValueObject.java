package cn.xialugui.identityaccess.domain.model.valueobject;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 夏露桂
 * @since 2021/7/15 17:59
 */
@Getter
@Setter
public class SinglePropertyValueObject<T extends SinglePropertyValueObject<T>> extends ValueObject<T> {
    protected String value;

    @Override
    protected boolean equalsTo(T other) {
        if (null == getValue() || null == other.getValue()) {
            return false;
        }
        return getValue().equals(other.getValue());
    }

    @Override
    protected int getHashCode() {
        return 0;
    }
}
