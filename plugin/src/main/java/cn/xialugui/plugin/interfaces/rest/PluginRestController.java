package cn.xialugui.plugin.interfaces.rest;

import cn.xialugui.plugin.command.api.PluginId;
import cn.xialugui.plugin.command.api.PublishPluginCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 夏露桂
 * @since 2021/12/29 19:11
 */
@RestController
@RequestMapping("plugins")
@RequiredArgsConstructor
public class PluginRestController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public void publish(@RequestBody Publish publish) {
        commandGateway.send(new PublishPluginCommand(new PluginId(), publish.getName(),
                publish.getDescription(),
                publish.getIcon()
        ));
    }
}
