package cn.xialugui.identityaccess.domain.model;

import java.io.Serializable;

/**
 * 值对象，概念类似整数。
 *
 * @author 夏露桂
 * @since 2021/4/9 18:55
 */
public abstract class ValueObject<T> implements Serializable {
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        T valueObject = (T) object;
        return equalsTo(valueObject);
    }


    protected abstract boolean equalsTo(T other);

    /*@Override
    public int hashCode() {
        return getHashCode();
    }

    protected abstract int getHashCode();*/
}
