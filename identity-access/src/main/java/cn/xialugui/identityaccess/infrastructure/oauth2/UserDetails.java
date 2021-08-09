package cn.xialugui.identityaccess.infrastructure.oauth2;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author 夏露桂
 * @since 2021/4/2 16:46
 */
@Getter
@Setter
public class UserDetails extends org.springframework.security.core.userdetails.User {
    private String id;


    public UserDetails(org.springframework.security.core.userdetails.UserDetails userDetails) {
        super(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
