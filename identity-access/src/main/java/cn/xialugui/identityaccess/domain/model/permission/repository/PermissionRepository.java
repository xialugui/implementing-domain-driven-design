package cn.xialugui.identityaccess.domain.model.permission.repository;

import cn.xialugui.identityaccess.domain.model.permission.aggregate.Permission;
import cn.xialugui.identityaccess.domain.model.permission.valueobject.PermissionId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author 夏露桂
 * @since 2021/7/23 16:56
 */
public interface PermissionRepository extends CrudRepository<Permission, Long> {

    Optional<Permission> findById(PermissionId permissionId);
}
