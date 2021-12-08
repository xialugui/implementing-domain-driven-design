package cn.xialugui.systeminformation.domain.model.token.event;

import cn.xialugui.systeminformation.domain.model.token.valueobject.TokenId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author 夏露桂
 * @since 2021/12/8 18:38
 */
@SuperBuilder
@Getter
@RequiredArgsConstructor
public class TokenEvent {
    private final TokenId tokenId;
}
