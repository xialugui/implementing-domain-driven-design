package cn.xialugui.plugin.domain.model.command;

import cn.xialugui.plugin.domain.model.PluginId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@SuperBuilder
@Getter
public class PluginCommand {
    @TargetAggregateIdentifier
    private final PluginId id;
}
