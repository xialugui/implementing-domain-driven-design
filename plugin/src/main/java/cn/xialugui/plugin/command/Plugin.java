package cn.xialugui.plugin.command;

import cn.xialugui.plugin.command.api.PluginId;
import cn.xialugui.plugin.command.api.PluginPublishedEvent;
import cn.xialugui.plugin.command.api.PublishPluginCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

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

    /**
     * 以url形式存储的图标。
     */
    private String icon;

    @CommandHandler
    public Plugin(PublishPluginCommand command) {
        apply(new PluginPublishedEvent(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getIcon()
        ));
    }

    @EventSourcingHandler
    public void on(PluginPublishedEvent event) {
        setId(event.getId());
        setName(event.getName());
        setDescription(event.getDescription());
        setIcon(event.getIcon());
    }
}
