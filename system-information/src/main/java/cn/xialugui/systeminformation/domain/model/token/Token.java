package cn.xialugui.systeminformation.domain.model.token;

import cn.xialugui.systeminformation.domain.model.token.command.IssueTokenCommand;
import cn.xialugui.systeminformation.domain.model.token.event.TokenIssuedEvent;
import cn.xialugui.systeminformation.domain.model.token.valueobject.TokenId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author 夏露桂
 * @since 2021/12/8 16:41
 */
@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
    @TargetAggregateIdentifier
    private TokenId tokenId;
    /**
     * 名称，客户端模式为客户端名称，授权码模式为用户名
     */
    private String name;

    @CommandHandler
    public Token(IssueTokenCommand command) {
        apply(TokenIssuedEvent.builder()
                .tokenId(command.getTokenId())
                .name(command.getName())
                .build());
    }

    @EventSourcingHandler
    public void on(TokenIssuedEvent event) {
        this.tokenId = event.getTokenId();
        this.name = event.getName();
    }
}
