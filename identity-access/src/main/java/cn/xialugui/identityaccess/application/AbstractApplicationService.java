package cn.xialugui.identityaccess.application;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * @author 夏露桂
 * @since 2021/8/30 10:35
 */
public abstract class AbstractApplicationService<T> {
    public void acceptIfExist(T object, Consumer<T> action) {
        if (null != object) {
            action.accept(object);
        } else {
            throw new IllegalArgumentException("对象不存在");
        }
    }

    public <O> O acceptIfExist(Optional<O> optional) {
        return optional.orElseThrow(() -> new IllegalArgumentException("对象不存在"));
    }

    public <T, U> void acceptIfExist(Optional<T> t, Optional<U> u, BiConsumer<T, U> action) {
        t.ifPresentOrElse(t1 -> u.ifPresentOrElse(u1 -> {
                    action.accept(t1, u1);
                }, () -> {
                    throw new IllegalArgumentException("对象不存在");
                }),
                () -> {
                    throw new IllegalArgumentException("对象不存在");
                });

    }

    public <O> O acceptIfExist(O object, UnaryOperator<O> operator) {
        if (null != object) {
            return operator.apply(object);
        } else {
            throw new IllegalArgumentException("对象不存在");
        }
    }

    public void acceptIfExist(Optional<T> optional, Consumer<T> action) {
        optional.ifPresentOrElse(
                action,
                () -> {
                    throw new IllegalArgumentException("对象不存在");
                }
        );
    }
}
