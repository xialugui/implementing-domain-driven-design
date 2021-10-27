package cn.xialugui.identityaccess.domain.model.user.entity;

import cn.xialugui.identityaccess.domain.model.role.aggregate.Role;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author 夏露桂
 * @since 2021/10/27 15:48
 */
@Validated
@Component
public class Temp {

    public void test(@NotNull Role role) {

    }
}
