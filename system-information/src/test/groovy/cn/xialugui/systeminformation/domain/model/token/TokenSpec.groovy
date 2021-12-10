package cn.xialugui.systeminformation.domain.model.token

import cn.xialugui.systeminformation.domain.model.authenticationlog.StubAggregateLifecycleSpecification
import cn.xialugui.systeminformation.domain.model.token.command.IssueTokenCommand
import cn.xialugui.systeminformation.domain.model.token.event.TokenIssuedEvent
import cn.xialugui.systeminformation.domain.model.token.valueobject.TokenId
import spock.lang.Unroll

class TokenSpec extends StubAggregateLifecycleSpecification {
    TokenId tokenId
    String name

    def setup() {
        name = "ddd"
        tokenId = TokenId.builder()
                .identifier("eyJraWQiOiI0ODcxM2U2OC05MTQ2LTQ4MTUtYjhjZS01MTEyODEyN2Q0YWMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJsaWJhaSIsImF1ZCI6ImRkZCIsIm5iZiI6MTYzODgwNzgzNSwic2NvcGUiOlsiZGRkLndyaXRlIiwiZGRkLmMiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjI0MDAwIiwiZXhwIjoxNjM4ODExNDM1LCJpYXQiOjE2Mzg4MDc4MzV9.lECuoil9x0nnVa4-KibI8mOjFBCmwQ1BoUY-v7tNfqXkKNQIKQxB4T1XtXC0fPTL4gUzR3dnFf6dQTgNE_g0iPXUNZGGdMEZX5zGYMYlTrSgRmqH1XGQLJ9dbn56b1t29MMbXnNSrswsudynLaHFIRJvAcijcz4FFfxSrDbZYRUhalNjZOoQtVc2IRsOCuRlpRRVKlTfCkOjqCr95TBnV2-nXXrym-jxuOpAbAOTS6nCLXkBpJ1plV_RaJN_I13OHJSQOY8foVoLtfIYTi94Sbg1NQQ4j4b3JcBr6qV9saTgkLiif3ZVdyVn9e4WQ1D7YD3tprjuJIFKVRItT5L7vw")
                .build()
    }

    def '发放令牌'() {
        when: '发放'
        new Token(
                IssueTokenCommand.builder()
                        .tokenId(tokenId)
                        .name(name)
                        .build()
        )
        then: '已发'
        expectEvent(
                TokenIssuedEvent.builder()
                        .tokenId(tokenId)
                        .name(name)
                        .build()
        )
    }

    @Unroll("令牌：#identifier，名称：#nameInput")
    def '发放令牌，令牌和名称必不为空'() {
        when: '发放令牌'
        new Token(
                IssueTokenCommand.builder()
                        .tokenId(TokenId.builder().identifier(identifier).build())
                        .name(nameInput)
                        .build()
        )
        then: '抛出相应异常'
        thrown(exception)
        where:
        identifier | nameInput || exception
        null       | "ddd"     || TokenIdEmptyException
        ""         | "ddd"     || TokenIdEmptyException
        "token"    | null      || TokenNameEmptyException
        "token"    | ""        || TokenNameEmptyException
        ""         | ""        || TokenIdEmptyException
        null       | null      || TokenIdEmptyException
    }

}
