package cn.xialugui.identityaccess.application.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeNicknameCommand {
    @Schema(description = "昵称", example = "李白")
    private String nickname;
}
