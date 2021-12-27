package cn.xialugui.plugin.domain.model.event;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@EqualsAndHashCode
public class PluginEvent {
    private final Long id;
}
