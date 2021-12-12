package cn.xialugui.systeminformation.query.token

import cn.xialugui.systeminformation.domain.model.token.event.TokenIssuedEvent
import cn.xialugui.systeminformation.domain.model.token.valueobject.TokenId
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("令牌说明")
@Subject(TokenViewEventHandler)
class TokenViewEventHandlerSpec extends Specification {
    TokenViewRepository repository = Mock()
    def tokenId = TokenId.builder()
            .identifier("eyJraWQiOiI0ODcxM2U2OC05MTQ2LTQ4MTUtYjhjZS01MTEyODEyN2Q0YWMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJsaWJhaSIsImF1ZCI6ImRkZCIsIm5iZiI6MTYzODgwNzgzNSwic2NvcGUiOlsiZGRkLndyaXRlIiwiZGRkLmMiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjI0MDAwIiwiZXhwIjoxNjM4ODExNDM1LCJpYXQiOjE2Mzg4MDc4MzV9.lECuoil9x0nnVa4-KibI8mOjFBCmwQ1BoUY-v7tNfqXkKNQIKQxB4T1XtXC0fPTL4gUzR3dnFf6dQTgNE_g0iPXUNZGGdMEZX5zGYMYlTrSgRmqH1XGQLJ9dbn56b1t29MMbXnNSrswsudynLaHFIRJvAcijcz4FFfxSrDbZYRUhalNjZOoQtVc2IRsOCuRlpRRVKlTfCkOjqCr95TBnV2-nXXrym-jxuOpAbAOTS6nCLXkBpJ1plV_RaJN_I13OHJSQOY8foVoLtfIYTi94Sbg1NQQ4j4b3JcBr6qV9saTgkLiif3ZVdyVn9e4WQ1D7YD3tprjuJIFKVRItT5L7vw")
            .build()
    def eventHandler
    def name = "ddd"

    def setup() {
        eventHandler = new TokenViewEventHandler(repository)
    }

    def "收到发放令牌事件，保存令牌"() {
        when: "收到发放令牌事件"
        eventHandler.on(TokenIssuedEvent.builder()
                .tokenId(tokenId)
                .name(name)
                .build())
        then: "保存令牌"
        1 * repository.save((TokenView entity) -> {
            with(entity) {
                tokenId == this.tokenId
                name == this.name
            }
        })

    }
}
