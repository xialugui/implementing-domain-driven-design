package cn.xialugui.systeminformation.domain.model.token.valueobject;

import cn.hutool.core.util.IdUtil;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * 令牌标识符
 *
 * @author 夏露桂
 * @since 2021/11/30 17:38
 */
@Value
@Builder
@Jacksonized
public class TokenId {
    public static final TokenId RANDOM = new TokenId(IdUtil.getSnowflake().nextId());
    Long identifier;
}

