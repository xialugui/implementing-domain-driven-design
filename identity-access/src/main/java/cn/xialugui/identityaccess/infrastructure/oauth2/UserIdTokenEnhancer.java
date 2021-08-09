package cn.xialugui.identityaccess.infrastructure.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 夏露桂
 * @since 2021/4/2 16:21
 */
@Slf4j
public class UserIdTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        UserDetails userDetails = ((UserDetails) authentication.getPrincipal());
        log.debug("authentication转为UserDetails，结果：{},id为：{}", userDetails, userDetails.getId());
        Map<String, Object> information = new HashMap<>();
        information.put("id", userDetails.getId());
        authentication.setDetails(information);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(information);
        return accessToken;
    }
}
