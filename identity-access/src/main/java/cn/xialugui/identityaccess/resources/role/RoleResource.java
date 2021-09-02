package cn.xialugui.identityaccess.resources.role;

import cn.xialugui.identityaccess.application.role.ChangeNameCommand;
import cn.xialugui.identityaccess.application.role.CreateCommand;
import cn.xialugui.identityaccess.application.role.RoleApplicationService;
import cn.xialugui.identityaccess.domain.model.role.aggragate.Role;
import com.lugew.winsin.web.Standard;
import io.swagger.v3.oas.annotations.Operation;
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
public class RoleResource {
    private final RoleApplicationService applicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "创建")
    public void create(@RequestBody CreateCommand createCommand) {
        applicationService.create(createCommand);
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
