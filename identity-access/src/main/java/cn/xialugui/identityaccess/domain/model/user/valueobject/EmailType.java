package cn.xialugui.identityaccess.domain.model.user.valueobject;

import java.util.HashMap;
import java.util.Map;

/**
 * 邮件类型值对象
 * <p>
 * 枚举是实现标准类型的简单方法，在此，只需简单添加名字即可。
 * 其它的描述信息{@link EmailType#NET_EASE}而不需其它描述信息，其它
 * 描述信息通常只有在UI用到，且有时文本需本地化处理，因此这种功能放在模型中不合适。
 * 另一方面，我们可以使用{@code toString()}方法来获取描述信息。
 * <p>
 * 标准类型的另一种实现方法是使用聚合来表示，每个聚合实例代表一种类型。但是，
 * 此时我们需要慎重考虑。作为消费方的限界上下文并不会维护标准类型。被
 * 大范围使用的标准类型应该在一个单独的限界上下文中进行维护。在向消费上下
 * 文提供标准类型的聚合时，我们应该保证这些聚合的不变性。这时，我们需要思
 * 考，这个不变的聚合实体还是一个真正意义上的实体吗?如果不是，我们应该将其
 * 建模成一个共享的值对象实例。
 *
 * @author 夏露桂
 * @since 2021/7/27 15:51
 */
public enum EmailType {
    UNKNOWN,
    NET_EASE,
    TENCENT,
    ALIBABA,
    CMCC,
    GOOGLE,
    BAIDU,
    ;
    private static final Map<String, EmailType> MAP = new HashMap<>(
    ) {{
        put("@163.com", NET_EASE);
        put("@qq.com", TENCENT);
        put("@aliyun.com", ALIBABA);
        put("@139.com", CMCC);
        put("@baidu.com", BAIDU);
        put("@gmail.com", GOOGLE);
    }};
    private static final char AT = '@';


    public static EmailType of(String email) {
        return MAP.getOrDefault(email.substring(email.lastIndexOf(AT)), UNKNOWN);
    }
}
