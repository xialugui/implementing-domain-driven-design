package cn.xialugui.identityaccess.domain.model.user.repository;

import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import cn.xialugui.identityaccess.domain.model.user.valueobject.Nickname;
import cn.xialugui.identityaccess.domain.model.user.valueobject.UserId;
import cn.xialugui.identityaccess.domain.model.user.valueobject.Username;

import java.util.HashSet;
import java.util.Set;

/**
 * 面向集合设计的资源库
 * <p>
 * 面向集合的资源库体现了原生DDD资源库模式的基本思想。它模拟了一个集合
 * 从资源库的接口来看，我们根本看不出其背后还存在着持久
 * 化机制，也感觉不到我们是在向存储区域中保存数据。
 * </p>
 * <p>
 * 以{@link UserCollection}为例我们可以将对象添加到集合中，这些对象将一直驻留在集合里，
 * 直到被删除为止。要对集合中的对象元素进行修改，我们只需要从集合中获得一个对象的引
 * 用，然后让对象自己修改自身的状态。在这个过程中，我们并没有在集合本身上做
 * 特殊的操作。修改之后的对象依然位于集合之中， 但此时该对象的状态和它先前
 * 在集合中状态已经不同了。
 * </p>
 * <p>
 * 一个资源库应该模拟一个Set集合。无论采用什么类型的持久化机制，我们都不应该允
 * 许多次添加同一个聚合实例。另外，当从资源库中获取到一个对象并对其进行修改时，我们
 * 并不需要
 * <p>重新保存</p>
 * 该对象到资源库中。参考{@link UserCollection#main(String[])}
 * </p>
 *
 * @author 夏露桂
 * @since 2021/8/5 15:04
 */
public class UserCollection implements UserRepositoryForCollection {
    private final Set<User> users = new HashSet<>(10);

    /**
     * 就个人来讲，我并不喜欢使这些方法返回Boolean类型的结果，因为对于一个
     * 添加方法来说，有时返回true并不能保证对实例的成功添加。因此，对于资源库来
     * 说，返回void可能是更好的方式。
     *
     * @param user 用户
     */
    public void add(User user) {
        users.add(user);
    }

    public User get(int i) {
        for (User user : users) {
            i--;
            if (i == 0) {
                return user;
            }
        }
        return null;
    }

    public User get(User user) {
        for (User user1 : users) {
            if (user1.equals(user)) {
                return user;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        UserCollection collection = new UserCollection();
        collection.add(new User(
                new UserId("11"),
                new Username("李", "白", "太白", "青莲居士"),
                new Nickname("二类"),
                null, null,
                null));
        User user = collection.get(0);
        user.changeNickname(new Nickname("李宝"));
        //在这里我们并没有必要也不存在对当前用户保存的方法，保存是隐式的。
        //这是面向集合设计的资源库和面向持久化设计的资源库之间的区别。
    }
}


interface UserRepositoryForCollection {
    void add(User user);

    User get(int i);

    User get(User user);
}