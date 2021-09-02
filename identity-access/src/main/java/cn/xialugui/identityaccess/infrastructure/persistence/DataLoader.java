package cn.xialugui.identityaccess.infrastructure.persistence;

import cn.xialugui.identityaccess.domain.model.role.aggragate.Role;
import cn.xialugui.identityaccess.domain.model.role.repository.RoleRepository;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleName;
import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import cn.xialugui.identityaccess.domain.model.user.repository.UserRepository;
import cn.xialugui.identityaccess.domain.model.user.valueobject.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.transaction.Transactional;

/**
 * 初始化数据的加载器
 *
 * @author 夏露桂
 * @since 2021/9/2 17:26
 */
@Component
@RequiredArgsConstructor
public class DataLoader {

    protected final PlatformTransactionManager transactionManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserId userId = UserId.random();
    private final Username username = new Username("libai");
    private final Nickname nickname = new Nickname("李白");
    private final Email email = new Email("libai@ddd.com");
    private final MobilePhone mobilePhone = new MobilePhone("13812345678");
    Password password;
    User user;

    private final Role role = new Role(
            RoleId.random(),
            new RoleName("ddd")
    );


    private void setupSpec() {
        password = Password.encodeOf("Ddd_5762566", passwordEncoder);

        user = new User(
                userId,
                username,
                nickname,
                email,
                mobilePhone,
                password
        );
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        event.getApplicationContext().getBean(DataLoader.class).load();
    }

    @Transactional
    public void load() {
        setupSpec();
        userRepository.save(user);
        roleRepository.save(role);
        userRepository.of(userId)
                .ifPresent(u -> {
                    u.addRole(role.getRoleId());
                    userRepository.save(u);
                });
    }

}
