package cn.xialugui.systeminformation.domain.model.authenticationlog.valueobject;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志细节
 *
 * @author 夏露桂
 * @since 2021/12/6 17:16
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public final class Detail {
    /**
     * 对象名称，包含用户名称/客户端名称
     */
    private final String name;
    /**
     * ip地址，识别本地/远程
     */
    private final String ip;
    /**
     * 备注，如用户名/密码错误
     */
    private final String remarks;
    /**
     * 类型
     */
    private final Type type;
    /**
     * 时间戳
     */
    private final Long timestamp;

    public enum Type {
        CLIENT("org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken"),
        ACCESS_TOKEN("org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken"),
        AUTHORIZATION_CODE("org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeAuthenticationToken"),
        AUTHORIZATION_CODE_REQUEST("org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken"),
        CLIENT_CREDENTIALS("org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientCredentialsAuthenticationToken"),
        REFRESH("org.springframework.security.oauth2.server.authorization.authentication.OAuth2RefreshTokenAuthenticationToken.OAuth2RefreshTokenAuthenticationToken"),
        INSTRO_SPECTION("org.springframework.security.oauth2.server.authorization.authentication.OAuth2TokenIntrospectionAuthenticationToken.OAuth2TokenIntrospectionAuthenticationToken"),
        REVOCATION("org.springframework.security.oauth2.server.authorization.authentication.OAuth2TokenRevocationAuthenticationToken.OAuth2TokenRevocationAuthenticationToken"),
        USERNAME_PASSWORD("org.springframework.security.authentication.UsernamePasswordAuthenticationToken"),
        UNKNOWN("unknown");
        private final String className;
        private static final Map<String, Type> TYPE_MAP = new HashMap<>() {{
            for (Type type : Type.values()) {
                put(type.className, type);
            }
        }};

        Type(String className) {
            this.className = className;
        }

        public static Type ofClassName(String className) {
            return TYPE_MAP.getOrDefault(className, UNKNOWN);
        }

        public static boolean isAccessToken(String className) {
            return ACCESS_TOKEN.equals(ofClassName(className));
        }
    }
}
