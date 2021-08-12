package cn.xialugui.identityaccess.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

/**
 * 聚合根
 * 属性{@code id}用来区分不同对象。
 *
 * @author 夏露桂
 * @since 2021/7/14 17:53
 */

@Getter
@Setter
public abstract class AggregateRoot<Root extends AbstractAggregateRoot<Root>> extends AbstractAggregateRoot<Root> {

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AggregateRoot)) {
            return false;
        }
        AggregateRoot<Root> other = (AggregateRoot<Root>) obj;
        //引用相等则必相等
        if (this == other)
            return true;
        if (!this.getClass().equals(other.getClass()))
            return false;
        //id相等则视为同一对象
        return equalsTo(other);
    }

    public abstract boolean equalsTo(AggregateRoot<Root> aggregateRoot);
}
