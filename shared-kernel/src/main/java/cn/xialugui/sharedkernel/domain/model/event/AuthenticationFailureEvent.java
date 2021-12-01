package cn.xialugui.sharedkernel.domain.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 夏露桂
 * @since 2021/12/1 16:31
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationFailureEvent {
    private String name;
    private String detail;
    private String remark;
    private Long timestamp;
}
