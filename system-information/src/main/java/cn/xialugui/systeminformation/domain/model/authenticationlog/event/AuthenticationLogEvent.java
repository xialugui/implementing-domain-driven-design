package cn.xialugui.systeminformation.domain.model.authenticationlog.event;

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author 夏露桂
 * @since 2021/11/30 20:08
 */
@RequiredArgsConstructor
@Getter
public class AuthenticationLogEvent {
    private final AuthenticationLogId authenticationLogId;
}
