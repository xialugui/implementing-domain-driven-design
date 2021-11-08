package cn.xialugui.identityaccess.domain.model;

import java.io.Serializable;

/**
 * 值对象，概念类似整数。
 *
 * @author 夏露桂
 * @since 2021/4/9 18:55
 */
public abstract class ValueObject<T> implements Serializable {

    /**
     * 通常来说，在比较相等性
     * 时，我们将省略掉对非null的检查。
     *
     * @param object 要比较的对象
     * @return 相等返回true
     */
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

    /**
     * 判断值对象是否相等，由派生类实现。
     *
     * @param other 比较对象
     * @return 相等返回true，否则返回false
     */
    protected abstract boolean equalsTo(T other);

    /*
      根据Java标准，hashCode()方法和equals()方法拥有相同的契约，即如果两个
      对象是相等的，那么它们的hashCode()方法也应该返回相同的结果。
     */
    /*@Override
    public int hashCode() {
        return getHashCode();
    }

    protected abstract int getHashCode();*/
}
