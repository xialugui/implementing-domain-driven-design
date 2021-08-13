package cn.xialugui.identityaccess.infrastructure.oauth2;

import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @author 夏露桂
 * @since 2021/8/11 15:24
 */
@Component
@AllArgsConstructor
public class DefaultUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        cn.xialugui.identityaccess.domain.model.user.aggregate.User result =
                userRepository.findByUsername_字(username);
        return User.builder()
                .username(result.getUsername().get号())
                .password(result.getPassword().getPassword())
                .roles(result.getRoleIds().stream().map(RoleId::getId).toArray(String[]::new))
                .build();

    }
}
