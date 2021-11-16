package cn.xialugui.sharedkernel.domain.model.user.valueobject;

import cn.xialugui.sharedkernel.domain.model.NaturalId;
import cn.xialugui.sharedkernel.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * 用户名（在这里使用中文便于阐述概念）
 * <p>
 * 显然用户名可以分为姓和名两个属性，它们是一组相关的属性，用户名不可或缺的组成部分。
 * 并不是任何属性都可以组合成值对象，
 * <p>
 * 中文字段仅作为说明，无实际用途。
 * </p>
 *
 * @author 夏露桂
 * @since 2021/7/15 17:50
 */
@Embeddable
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Username extends ValueObject<Username> implements NaturalId {

    private @NotNull String value;

    public Username(String value) {
        setValue(value);
    }

    @Override
    protected boolean equalsTo(Username other) {
        return getValue().equals(other.getValue());
    }
}
