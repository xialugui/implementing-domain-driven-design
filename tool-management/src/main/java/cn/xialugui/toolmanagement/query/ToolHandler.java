package cn.xialugui.toolmanagement.query;

import cn.xialugui.toolmanagement.command.api.ToolPublishedEvent;
import cn.xialugui.toolmanagement.query.api.FindTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ToolHandler {
    private final ToolEntityRepository repository;

    @EventHandler
    void on(ToolPublishedEvent event) {
        ToolEntity entity = new ToolEntity(event.getToolId().getIdentifier(),
                event.getName(), event.getDescription(), event.getIcon()
        );
        repository.save(entity);
    }

    @QueryHandler
    Page handle(FindTools query) {
        return repository.findAllByNameContains(query.getName(), query.getPageable());
    }
}
