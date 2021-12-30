package cn.xialugui.plugin.query;

import cn.xialugui.plugin.command.api.PluginPublishedEvent;
import cn.xialugui.plugin.query.api.FindPlugins;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
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

    @QueryHandler
    Page handle(FindPlugins query) {
        return repository.findAllByNameContains(query.getName(), query.getPageable());
    }
}
