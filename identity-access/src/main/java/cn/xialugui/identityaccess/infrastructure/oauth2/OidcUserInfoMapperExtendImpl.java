package cn.xialugui.identityaccess.infrastructure.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author aLing created at 2022/5/5 9:50
 */
@Slf4j
@Component
public class OidcUserInfoMapperExtendImpl implements XyOidcUserInfoMapper.OidcUserInfoMapperExtend {

    private static final Map<String, List<String>> SCOPE2_CLAIMS_MAP = new HashMap<>();

    static {
        SCOPE2_CLAIMS_MAP.put(OidcScopes.PROFILE, Collections.singletonList("userName"));
        SCOPE2_CLAIMS_MAP.put(OidcScopes.PHONE, Collections.singletonList("mobilePhone"));
        SCOPE2_CLAIMS_MAP.put(OidcScopes.EMAIL, Collections.singletonList("email"));
        SCOPE2_CLAIMS_MAP.put(OidcScopes.ADDRESS, Arrays.asList("postalAddress", "postCode"));
    }


    @Override
    public void extendClaims(Map<String, Object> oidcUserInfoClaims, OidcUserInfoAuthenticationContext authenticationContext) {
        String userId = authenticationContext.getAuthorization().getPrincipalName();
//        OAuth2AccessToken accessToken = authenticationContext.getAccessToken();

        /** 查询并填充用户信息 */
        Map<String, Object> userInfoMap = this.buildUserInfoMap(userId);
        oidcUserInfoClaims.putAll(userInfoMap);
        log.info("extend OIDC userInfo from {}: {}", userId, userInfoMap);
    }

    /**
     * 构建用户Map
     *
     * @param userId 用户ID
     * @return Map(propName, propVal)
     */
    private Map<String, Object> buildUserInfoMap(String userId) {
        Map<String, Object> propMap = new HashMap<>();
        propMap.put("userId", userId);
        propMap.put("userName", "test");
        propMap.put("mobilePhone", "18888888888");
        propMap.put("email", "test@email.com");
        propMap.put("postalAddress", "中国浙江省杭州市");
        propMap.put("postCode", "340000");
        return propMap;
    }
}
