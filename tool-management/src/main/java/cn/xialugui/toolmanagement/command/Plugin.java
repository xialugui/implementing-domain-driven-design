package cn.xialugui.toolmanagement.command;

import cn.xialugui.toolmanagement.command.api.PluginId;
import cn.xialugui.toolmanagement.command.api.PluginPublishedEvent;
import cn.xialugui.toolmanagement.command.api.PublishPluginCommand;
import cn.xialugui.toolmanagement.util.PluginUtil;
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
        unpack(command.getFilename(), command.getPluginId());
        apply(new PluginPublishedEvent(
                command.getPluginId(),
                command.getName(),
                command.getDescription(),
                command.getIcon()
        ));
    }

    protected void unpack(String filename, PluginId pluginId) {
        PluginUtil.unpack(filename, getDestination(pluginId));
    }

    private String getDestination(PluginId pluginId) {
        return pluginId.toString();
    }

    @EventSourcingHandler
    public void on(PluginPublishedEvent event) {
        setId(event.getPluginId());
    }
}
