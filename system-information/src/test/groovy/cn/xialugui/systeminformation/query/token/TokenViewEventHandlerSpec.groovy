package cn.xialugui.systeminformation.query.token

import cn.xialugui.sharedkernel.domain.model.event.TokenRevocatedEvent
import cn.xialugui.systeminformation.domain.model.token.event.TokenIssuedEvent
import cn.xialugui.systeminformation.domain.model.token.valueobject.TokenId
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("令牌说明")
@Subject(TokenViewEventHandler)
class TokenViewEventHandlerSpec extends Specification {
    TokenViewRepository repository = Mock()
    def eventHandler
    def name = "ddd"
    def tokenValue = "eyJraWQiOiI0ODcxM2U2OC05MTQ2LTQ4MTUtYjhjZS01MTEyODEyN2Q0YWMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJsaWJhaSIsImF1ZCI6ImRkZCIsIm5iZiI6MTYzODgwNzgzNSwic2NvcGUiOlsiZGRkLndyaXRlIiwiZGRkLmMiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjI0MDAwIiwiZXhwIjoxNjM4ODExNDM1LCJpYXQiOjE2Mzg4MDc4MzV9.lECuoil9x0nnVa4-KibI8mOjFBCmwQ1BoUY-v7tNfqXkKNQIKQxB4T1XtXC0fPTL4gUzR3dnFf6dQTgNE_g0iPXUNZGGdMEZX5zGYMYlTrSgRmqH1XGQLJ9dbn56b1t29MMbXnNSrswsudynLaHFIRJvAcijcz4FFfxSrDbZYRUhalNjZOoQtVc2IRsOCuRlpRRVKlTfCkOjqCr95TBnV2-nXXrym-jxuOpAbAOTS6nCLXkBpJ1plV_RaJN_I13OHJSQOY8foVoLtfIYTi94Sbg1NQQ4j4b3JcBr6qV9saTgkLiif3ZVdyVn9e4WQ1D7YD3tprjuJIFKVRItT5L7vw"
    def tokenId = TokenId.RANDOM

    def setup() {
        eventHandler = new TokenViewEventHandler(repository)
    }

    def "收到发放令牌事件，保存令牌"() {
        when: "收到发放令牌事件"
        TokenIssuedEvent event = TokenIssuedEvent.builder()
                .tokenId(tokenId)
                .tokenValue(tokenValue)
                .name(name)
                .build()
        eventHandler.on(event)
        then: "保存令牌"
        1 * repository.save((TokenView entity) -> {
            with(entity) {
                token == event.tokenId.identifier
                name == event.name
                tokenValue == event.tokenValue
                status == Status.NORMAL
            }
        })
    }

    def "令牌撤回，保存"() {

        when: "收到撤回令牌事件"
        TokenRevocatedEvent event = TokenRevocatedEvent.builder()
                .tokenValue(tokenValue)
                .name(name)
                .detail("令牌撤回")
                .build()

        eventHandler.on(event)
        then: "修改令牌状态为撤回"
        1 * repository.save((TokenView entity) -> {
            with(entity) {
                token == event.tokenValue
                name == event.name
                status == Status.REVOCATED
            }
        })
    }
}
