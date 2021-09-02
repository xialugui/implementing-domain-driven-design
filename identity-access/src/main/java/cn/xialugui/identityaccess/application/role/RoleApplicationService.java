package cn.xialugui.identityaccess.application.role;

import cn.xialugui.identityaccess.application.AbstractApplicationService;
import cn.xialugui.identityaccess.domain.model.role.aggragate.Role;
import cn.xialugui.identityaccess.domain.model.role.repository.RoleRepository;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleName;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

/**
 * @author 夏露桂
 * @since 2021/8/17 15:52
 */
@Service
@AllArgsConstructor
public class RoleApplicationService extends AbstractApplicationService<Role> {

    private final RoleRepository repository;

    @Transactional
    public void create(CreateCommand command) {
        repository.save(new Role(
                RoleId.random(),
                new RoleName(command.getName())
        ));

    }

    @Transactional
    public void changeName(String roleId, ChangeNameCommand command) {
        repository.findByRoleId(new RoleId(roleId))
                .ifPresentOrElse(role -> {
                    role.changeName(new RoleName(command.getName()));
                    repository.save(role);
                }, () -> {
                    throw new IllegalArgumentException("角色不存在");
                });
    }
    public Role of(String roleId) {
        return acceptIfExist(repository.findByRoleId(new RoleId(roleId)));
    }
}
