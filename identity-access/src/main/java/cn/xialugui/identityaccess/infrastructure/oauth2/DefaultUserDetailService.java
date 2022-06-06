package cn.xialugui.identityaccess.infrastructure.oauth2;

import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository;
import cn.xialugui.identityaccess.domain.model.user.valueobject.Username;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 夏露桂
 * @since 2021/8/11 15:24
 */
@Component
@AllArgsConstructor
public class DefaultUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final EventGateway eventGateway;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<cn.xialugui.identityaccess.domain.model.user.aggregate.User> result = userRepository.findByUsername(new Username(username));
        AtomicReference<UserDetails> userDetailsAtomicReference = new AtomicReference<>();
        result.ifPresentOrElse(user ->
                        userDetailsAtomicReference.set(User.builder().username(user.getUsername().toString()).password(user.getPassword().getPassword()).authorities("book").roles(user.getRoleIds().stream().map(RoleId::getValue).toArray(String[]::new)).build()),
                () -> {
                    throw new UsernameNotFoundException("用户未注册");
                });

        return userDetailsAtomicReference.get();
    }
}
