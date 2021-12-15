package cn.xialugui.systeminformation.domain.model.token.event;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class TokenEvent {
    private final Long tokenId;
}
