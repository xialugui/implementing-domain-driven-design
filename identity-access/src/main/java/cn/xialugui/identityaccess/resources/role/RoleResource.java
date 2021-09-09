package cn.xialugui.identityaccess.resources.role;

import cn.xialugui.identityaccess.application.role.ChangeNameCommand;
import cn.xialugui.identityaccess.application.role.CreateRoleCommand;
import cn.xialugui.identityaccess.application.role.RoleApplicationService;
import cn.xialugui.identityaccess.domain.model.role.aggregate.Role;
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
@RequestMapping("roles")
@RestController
@RequiredArgsConstructor
@Standard
@Tag(name = "角色资源")
public class RoleResource {
    private final RoleApplicationService applicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "创建")
    public void create(@RequestBody CreateRoleCommand createRoleCommand) {
        applicationService.create(createRoleCommand);
    }

    @PutMapping("{roleId}")
    @Operation(summary = "修改角色名")
    public void changeName(@PathVariable("roleId") String roleId, @RequestBody ChangeNameCommand command) {
        applicationService.changeName(roleId, command);
    }

    @GetMapping("{roleId}")
    @Operation(summary = "获取角色")
    public Role of(@PathVariable("roleId") String roleId) {
        return applicationService.of(roleId);
    }
}
