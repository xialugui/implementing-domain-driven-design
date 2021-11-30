package cn.xialugui.systeminformation.domain.model.authenticationlog;

import cn.hutool.core.util.IdUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 夏露桂
 * @since 2021/11/30 17:38
 */
@Getter
@Setter(AccessLevel.PRIVATE)
public class AuthenticationLogId {
    private final Long identifier;

    public AuthenticationLogId() {
        this.identifier = IdUtil.getSnowflake().nextId();
    }

    @Override
    public String toString() {
        return identifier.toString();

    }
}
