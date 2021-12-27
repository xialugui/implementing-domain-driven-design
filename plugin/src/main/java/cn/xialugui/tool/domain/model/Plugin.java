package cn.xialugui.tool.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * @author 夏露桂
 * @since 2021/12/27 20:06
 */
@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PRIVATE)
public class Plugin {
    @AggregateIdentifier
    private PluginId id;

    /**
     * 名称，如：计算器。
     */
    private String name;

    /**
     * 描述，如：这是一个很好的计算器，试试看吧。
     */
    private String description;


}
