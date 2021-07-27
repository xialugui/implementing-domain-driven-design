package cn.xialugui.filecollaboration.domain.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 实体
 * 实体拥有{@code id}属性，用以区分不同对象。而值对象不存在{@code id}属性，其所有属性都是不可变的，
 * 这种不可变特性使得我们可以将其看作整形变量。
 *
 * @author 夏露桂
 * @since 2021/7/14 18:03
 */
@Getter
@Setter
public class Entity {

    protected Long id;

    /**
     * 判断两个实体是否相等。
     *
     * @param object 另一被比较的对象
     * @return {@code id}相同或者引用相同返回{@code true}
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Entity)) {
            return false;
        }
        Entity other = (Entity) object;
        //引用相等则必相等
        if (this == other)
            return true;
        if (!this.getClass().equals(other.getClass()))
            return false;
        if (null == getId() || null == other.getId())
            return false;
        //id相等则视为同一对象
        return getId().equals(other.getId());
    }

}
