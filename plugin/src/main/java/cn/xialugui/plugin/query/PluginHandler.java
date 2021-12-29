package cn.xialugui.plugin.query;

import cn.xialugui.plugin.command.api.PluginPublishedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PluginHandler {
    private final PluginEntityRepository repository;

    @EventHandler
    void on(PluginPublishedEvent event) {
        PluginEntity entity = new PluginEntity(event.getPluginId().getIdentifier(),
                event.getName(), event.getDescription(), event.getIcon()
        );
        repository.save(entity);
    }
}
