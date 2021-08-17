package cn.xialugui.identityaccess.resources.user;

import cn.xialugui.identityaccess.application.user.CreateCommand;
import cn.xialugui.identityaccess.application.user.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author 夏露桂
 * @since 2021/8/5 10:41
 */
@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UserResource {
    private final UserApplicationService userApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "注册")
    public void register(@RequestBody CreateCommand createCommand) {
        userApplicationService.register(createCommand);
    }

    @GetMapping("{userId}")
    @Operation(summary = "获取信息")
    public UserDetailsProjection detailsOf(@PathVariable String userId) {
        return userApplicationService.detailsOf(userId);
    }

}
