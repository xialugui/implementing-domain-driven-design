package cn.xialugui.identityaccess.resources.user;

import cn.xialugui.identityaccess.application.user.ChangeNicknameCommand;
import cn.xialugui.identityaccess.application.user.CreateUserCommand;
import cn.xialugui.identityaccess.application.user.UserApplicationService;
import com.lugew.winsin.web.Standard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author 夏露桂
 * @since 2021/8/5 10:41
 */
@RequestMapping("users")
@RestController
@RequiredArgsConstructor
@Standard
@Tag(name = "用户资源")
public class UserResource {
    private final UserApplicationService userApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "注册")
    public void register(@RequestBody CreateUserCommand createUserCommand) {
        userApplicationService.register(createUserCommand);
    }


    @GetMapping("{userId}")
    @Operation(summary = "获取用户简要信息")
    public UserDetailsProjection detailsOf(@PathVariable @Parameter(name = "userId", description = "用户id") String userId) {
        return userApplicationService.detailsOf(userId);
    }

    @PutMapping("{userId}")
    @Operation(summary = "修改昵称")
    public void changNickname(@PathVariable @Parameter(name = "userId", description = "用户id") String userId, @RequestBody ChangeNicknameCommand command) {
        userApplicationService.changeNickname(userId, command);
    }

    @PutMapping("{userId}/role/{roleId}")
    @Operation(summary = "增加角色")
    public void addRole(@PathVariable @Parameter(name = "userId", description = "用户id") String userId,
                        @PathVariable @Parameter(name = "roleId", description = "角色id") String roleId) {
        userApplicationService.addRole(userId, roleId);
    }

}
