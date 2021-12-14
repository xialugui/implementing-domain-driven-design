package cn.xialugui.systeminformation.domain.model.token;

import cn.xialugui.systeminformation.domain.model.token.command.IssueTokenCommand;
import cn.xialugui.systeminformation.domain.model.token.event.TokenIssuedEvent;
import cn.xialugui.systeminformation.domain.model.token.valueobject.TokenId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @author 夏露桂
 * @since 2021/12/8 16:41
 */
@Aggregate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
    @AggregateIdentifier
    private TokenId tokenId;
    /**
     * 令牌值，形如：eyJraWQiOiI3Zjc5YjQyNC04N2Q1LTQ2NWQtODA5Zi1kYjAzNjA1OTBkNjEiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJsaWJhaSIsImF1ZCI6ImRkZCIsIm5iZiI6MTYzOTQ5NDcxMywic2NvcGUiOlsiZGRkLndyaXRlIiwib3BlbmlkIiwiZGRkLmMiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjI0MDAwIiwiZXhwIjoxNjM5NDk4MzEzLCJpYXQiOjE2Mzk0OTQ3MTN9.1q8Eo7gKzW-CgXwji8P6i-O5sUVHvob7ma7vXkwTkwPFQJZOUpPsyh2WSA5GTxstAkezcqzjXa9InR6WdZfeOcvrfBkxaJwRT1fIUnR7TIe3Iz4bDl_ZWFZJGjmVNRdOBdERtr4QY93YvgrWr4u_MnZ-B9sS7aTt0xEhlcAjZLJsOz_ABTT3dTtWHjppyzol1_8Wao92RraA6IB8P4H4UrTILKubyUdhjhD7aie28P03CW0q0Di8Z-lyxA8fFZOnJXTL2KjImwWxXV1VrWiIbLnYlAmrRjtcC0BbP5gdz8V57XRP-jL5MWGX2J2quKd-DDuq5yRI2W59Z_R9Dyv7Pw
     */
    private String tokenValue;
    /**
     * 名称，客户端模式为客户端名称，授权码模式为用户名
     */
    private String name;

    @CommandHandler
    public Token(IssueTokenCommand command) {
        if (!StringUtils.isNotEmpty(command.getTokenId().getIdentifier().toString())) {
            throw new TokenValueEmptyException();
        }

        if (!StringUtils.isNotEmpty(command.getName())) {
            throw new TokenNameEmptyException();
        }
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
