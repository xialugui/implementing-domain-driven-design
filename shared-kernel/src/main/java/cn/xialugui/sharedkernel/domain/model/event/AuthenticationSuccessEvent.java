package cn.xialugui.sharedkernel.domain.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 夏露桂
 * @since 2021/12/1 16:31
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationSuccessEvent {
    private String name;
    private String detail;
    private Long timestamp;
}
