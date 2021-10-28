package cn.xialugui.identityaccess.application.permission;

import cn.xialugui.identityaccess.application.AbstractApplicationService;
import cn.xialugui.identityaccess.domain.model.permission.aggregate.Permission;
import cn.xialugui.identityaccess.domain.model.permission.repository.PermissionRepository;
import cn.xialugui.identityaccess.domain.model.permission.valueobject.PermissionId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author 夏露桂
 * @since 2021/8/5 11:00
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PermissionApplicationService extends AbstractApplicationService<Permission> {
    private final PermissionRepository repository;

    public void create(CreatePermissionCommand command) {
        repository.save(new Permission(
                new PermissionId(command.getId()),
                command.getName()
        ));
    }
}
