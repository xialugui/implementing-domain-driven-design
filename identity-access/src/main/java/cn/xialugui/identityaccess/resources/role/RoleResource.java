package cn.xialugui.identityaccess.resources.role;

import cn.xialugui.identityaccess.application.role.CreateCommand;
import cn.xialugui.identityaccess.application.role.RoleApplicationService;
import com.lugew.winsin.web.Standard;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.RollbackException;

/**
 * @author 夏露桂
 * @since 2021/8/26 17:42
 */
@RequestMapping("roles")
@RestController
@RequiredArgsConstructor
@Standard
public class RoleResource {
    private final RoleApplicationService applicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "创建")
    public void create(@RequestBody CreateCommand createCommand) {
        applicationService.create(createCommand);
    }

}
