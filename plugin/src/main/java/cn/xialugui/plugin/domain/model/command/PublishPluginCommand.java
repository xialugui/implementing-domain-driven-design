package cn.xialugui.plugin.domain.model.command;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
public class PublishPluginCommand extends PluginCommand {
    String name;
    String description;
    String icon;
}
