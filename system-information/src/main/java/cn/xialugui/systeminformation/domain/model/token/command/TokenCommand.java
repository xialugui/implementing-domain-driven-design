package cn.xialugui.systeminformation.domain.model.token.command;

import cn.xialugui.systeminformation.domain.model.token.valueobject.TokenId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author 夏露桂
 * @since 2021/12/8 16:47
 */
@SuperBuilder
@Getter
@RequiredArgsConstructor
public class TokenCommand {
    @TargetAggregateIdentifier
    private final TokenId tokenId;
}
