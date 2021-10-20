package cn.xialugui.identityaccess.domain.model.role.repository;

import cn.xialugui.identityaccess.domain.model.role.aggregate.Role;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author 夏露桂
 * @since 2021/7/23 16:56
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findById(RoleId roleId);
}
