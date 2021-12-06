package cn.xialugui.systeminformation.domain.model.authenticationlog.event;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.*;

/**
 * @author 夏露桂
 * @since 2021/11/30 20:08
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationLogEvent {
    private AuthenticationLogId authenticationLogId;
    private String name;
    private String ip;

    private String remarks;
    private String type;

    private Long timestamp;
}
