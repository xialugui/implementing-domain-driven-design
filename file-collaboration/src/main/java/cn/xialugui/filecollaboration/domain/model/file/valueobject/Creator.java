package cn.xialugui.filecollaboration.domain.model.file.valueobject;

import cn.xialugui.filecollaboration.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter(AccessLevel.PRIVATE)
public final class Creator extends ValueObject<Creator> {

    private String id;
    /**
     * name属性是身份与访问上下文中的User对象的用户名。
     * 当对象用户名发生改变时，如何关联变化？
     * <p>
     *      <li>
     *          可以使用领域事件来解决，当前限界上下文订阅身份与访问上下文的领域事件，当
     *          收到外部时间通知时，对事件进行处理。更新此对象。这样做可能会增加复杂度，但是
     *          做能够创建出更加具有自治性的系统。在自治系统中，我们可以将对象查找限定在本地对象中。
     *          这并不是说将外部对象缓存在本地系统中，而是将外部概念翻译成本地限界上下文中的概念。
     *      </li>
     * </p>
     */
    private String name;

    public Creator(String id) {
        setId(id);
    }

    @Override
    protected boolean equalsTo(Creator other) {
        return false;
    }
}
