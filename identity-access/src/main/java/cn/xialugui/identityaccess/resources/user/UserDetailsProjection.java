package cn.xialugui.identityaccess.resources.user;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author 夏露桂
 * @since 2021/8/12 16:41
 */
public interface UserDetailsProjection {
    @Schema(description = "用户id", example = "basfasdf")
    String getNaturalId();

    @Schema(description = "昵称", example = "李白")
    String getNickname();

    @Schema(description = "用户名", example = "libai")
    String getUsername();

    @Schema(description = "邮箱", example = "libai@139.com")
    String getEmail();
}
