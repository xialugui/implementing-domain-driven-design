package cn.xialugui.systeminformation.domain.model.authenticationlog;

import cn.hutool.core.util.IdUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * @author 夏露桂
 * @since 2021/11/30 17:38
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationLogId {
    Long identifier;

    public AuthenticationLogId() {
        this.identifier = IdUtil.getSnowflake().nextId();
    }
}
