package cn.xialugui.systeminformation.domain.model.token.valueobject;

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
    /**
     * 令牌标志通常类似：eyJraWQiOiI0ODcxM2U2OC05MTQ2LTQ4MTUtYjhjZS01MTEyODEyN2Q0YWMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJsaWJhaSIsImF1ZCI6ImRkZCIsIm5iZiI6MTYzODgwNzgzNSwic2NvcGUiOlsiZGRkLndyaXRlIiwiZGRkLmMiXSwiaXNzIjoiaHR0cDpcL1wvbG9jYWxob3N0OjI0MDAwIiwiZXhwIjoxNjM4ODExNDM1LCJpYXQiOjE2Mzg4MDc4MzV9.lECuoil9x0nnVa4-KibI8mOjFBCmwQ1BoUY-v7tNfqXkKNQIKQxB4T1XtXC0fPTL4gUzR3dnFf6dQTgNE_g0iPXUNZGGdMEZX5zGYMYlTrSgRmqH1XGQLJ9dbn56b1t29MMbXnNSrswsudynLaHFIRJvAcijcz4FFfxSrDbZYRUhalNjZOoQtVc2IRsOCuRlpRRVKlTfCkOjqCr95TBnV2-nXXrym-jxuOpAbAOTS6nCLXkBpJ1plV_RaJN_I13OHJSQOY8foVoLtfIYTi94Sbg1NQQ4j4b3JcBr6qV9saTgkLiif3ZVdyVn9e4WQ1D7YD3tprjuJIFKVRItT5L7vw
     */
    String identifier;
}

