package cn.xialugui.identityaccess.resources.user;

import cn.xialugui.identityaccess.application.user.CreateCommand;
import cn.xialugui.identityaccess.application.user.UserService;
import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
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
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "注册")
    public void register(@RequestBody CreateCommand createCommand) {
        userService.register(createCommand);
    }

    @GetMapping("{id}")
    @Operation(summary = "获取信息")
    public User info(@PathVariable String id) {
        return new User();
    }

}
