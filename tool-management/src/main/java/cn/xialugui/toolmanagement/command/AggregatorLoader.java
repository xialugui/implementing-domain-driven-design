package cn.xialugui.toolmanagement.command;

import cn.xialugui.toolmanagement.command.api.PublishToolCommand;
import cn.xialugui.toolmanagement.command.api.ToolId;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * 初始化数据的加载器
 *
 * @author 夏露桂
 * @since 2021/9/2 17:26
 */
@Component
@RequiredArgsConstructor
public class AggregatorLoader {

    protected final Repository<Tool> repository;
    private static final ToolId TOOL_ID = new ToolId(0);
    private static final String NAME = "开发者工具";
    private static final String DESCRIPTION = "开发者工具";
    private static final String ICON = "";


    private void setupSpec() {

    }

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBean(AggregatorLoader.class).load();
    }

    @Transactional
    public void load() {
        setupSpec();
//        repository.loadOrCreate(TOOL_ID.toString(), () -> new Tool(new PublishToolCommand(TOOL_ID, NAME, DESCRIPTION, ICON)))
    }

}
