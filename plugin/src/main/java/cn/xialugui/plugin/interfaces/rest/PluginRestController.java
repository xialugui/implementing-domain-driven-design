package cn.xialugui.plugin.interfaces.rest;

import cn.xialugui.plugin.command.api.PluginId;
import cn.xialugui.plugin.command.api.PublishPluginCommand;
import cn.xialugui.plugin.query.api.FindPlugins;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

/**
 * @author 夏露桂
 * @since 2021/12/29 19:11
 */
@RestController
@RequestMapping("plugins")
@RequiredArgsConstructor
@Slf4j
public class PluginRestController {

    private final ReactorCommandGateway commandGateway;
    private final ReactorQueryGateway queryGateway;

    @PostMapping
    public void publish(@RequestBody Publish publish, MultipartFile file) {
        commandGateway.send(new PublishPluginCommand(new PluginId(), publish.getName(),
                publish.getDescription(),
                publish.getIcon(),
                file.getOriginalFilename()
        ));
    }

    @GetMapping
    public Mono<Page> all(@RequestParam(required = false) String name, Pageable pageable) {
        return queryGateway.query(new FindPlugins(name, pageable),
                ResponseTypes.instanceOf(Page.class
                ));
    }
}
