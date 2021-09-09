package cn.xialugui.identityaccess.application.permission;

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
public class CreatePermissionCommand {
    private String id;
    private String name;
}
