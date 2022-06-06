package cn.xialugui.identityaccess.infrastructure.oauth2;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@JsonSerialize
public class UserTest extends User {
    private String otherInformation;

    public UserTest(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserTest(String username, String password, Collection<? extends GrantedAuthority> authorities, String otherInformation) {
        super(username, password, authorities);
        this.otherInformation = otherInformation;
    }
}
