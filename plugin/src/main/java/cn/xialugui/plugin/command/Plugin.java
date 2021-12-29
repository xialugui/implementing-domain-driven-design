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


    @CommandHandler
    public Plugin(PublishPluginCommand command) {
        apply(new PluginPublishedEvent(
                command.getPluginId(),
                command.getName(),
                command.getDescription(),
                command.getIcon()
        ));
    }

    @EventSourcingHandler
    public void on(PluginPublishedEvent event) {
        setId(event.getPluginId());
    }
}
