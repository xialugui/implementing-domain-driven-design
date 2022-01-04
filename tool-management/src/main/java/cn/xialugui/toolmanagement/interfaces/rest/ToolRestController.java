package cn.xialugui.toolmanagement.interfaces.rest;

import cn.xialugui.toolmanagement.command.api.PublishToolCommand;
import cn.xialugui.toolmanagement.command.api.ToolId;
import cn.xialugui.toolmanagement.query.api.FindTools;
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
@RequestMapping("tools")
@RequiredArgsConstructor
@Slf4j
public class ToolRestController {

    private final ReactorCommandGateway commandGateway;
    private final ReactorQueryGateway queryGateway;

    @PostMapping
    public void publish(@RequestBody Publish publish, MultipartFile file) {
        commandGateway.send(new PublishToolCommand(new ToolId(), publish.getName(),
                publish.getDescription(),
                publish.getIcon(),
                file.getOriginalFilename()
        ));
    }

    @GetMapping
    public Mono<Page> all(@RequestParam(required = false) String name, Pageable pageable) {
        return queryGateway.query(new FindTools(name, pageable),
                ResponseTypes.instanceOf(Page.class
                ));
    }
}
