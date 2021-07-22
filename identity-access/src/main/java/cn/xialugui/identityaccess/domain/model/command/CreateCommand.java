package cn.xialugui.identityaccess.domain.model.command;

import lombok.Getter;
import lombok.Setter;

/**
 * 创建命令
 *
 * @author 夏露桂
 * @since 2021/7/16 15:48
 */
@Getter
@Setter
public class CreateCommand {
    private String username;
    private String nickname;
    private String password;
    private String mobilePhone;
    private String email;
}
