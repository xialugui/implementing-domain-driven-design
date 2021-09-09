package cn.xialugui.identityaccess.resources.permission;

import cn.xialugui.identityaccess.application.permission.CreatePermissionCommand;
import cn.xialugui.identityaccess.application.permission.PermissionApplicationService;
import com.lugew.winsin.web.Standard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author 夏露桂
 * @since 2021/8/26 17:42
 */
@RequestMapping("permissions")
@RestController
@RequiredArgsConstructor
@Standard
@Tag(name = "权限资源")
public class PermissionResource {
    private final PermissionApplicationService applicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "创建")
    public void create(@RequestBody CreatePermissionCommand command) {
        applicationService.create(command);
    }
}
