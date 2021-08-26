package cn.xialugui.identityaccess.domain.model.role.repository;

import cn.xialugui.identityaccess.domain.model.role.aggragate.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 夏露桂
 * @since 2021/7/23 16:56
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
}
