package cn.xialugui.toolmanagement.command;

import cn.xialugui.toolmanagement.command.api.ToolId;
import cn.xialugui.toolmanagement.command.api.ToolPublishedEvent;
import cn.xialugui.toolmanagement.command.api.PublishToolCommand;
import cn.xialugui.toolmanagement.util.ToolUtil;
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
public class Tool {
    @AggregateIdentifier
    private ToolId id;


    @CommandHandler
    public Tool(PublishToolCommand command) {
        unpack(command.getFilename(), command.getToolId());
        apply(new ToolPublishedEvent(
                command.getToolId(),
                command.getName(),
                command.getDescription(),
                command.getIcon()
        ));
    }

    protected void unpack(String filename, ToolId toolId) {
        ToolUtil.unpack(filename, getDestination(toolId));
    }

    private String getDestination(ToolId toolId) {
        return toolId.toString();
    }

    @EventSourcingHandler
    public void on(ToolPublishedEvent event) {
        setId(event.getToolId());
    }
}
