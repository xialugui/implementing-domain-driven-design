package cn.xialugui.plugin.domain.model.event;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Value
public class PluginPublishedEvent extends PluginEvent {
    String name;
    String description;
    String icon;
}
