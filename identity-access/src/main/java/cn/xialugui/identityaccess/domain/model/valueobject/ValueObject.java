package cn.xialugui.identityaccess.domain.model.valueobject;

/**
 * 值对象，概念类似整数。
 *
 * @author 夏露桂
 * @since 2021/4/9 18:55
 */
public abstract class ValueObject<T> {
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

    @Override
    public int hashCode() {
        return getHashCode();
    }

    protected abstract boolean equalsTo(T other);

    protected abstract int getHashCode();
}
