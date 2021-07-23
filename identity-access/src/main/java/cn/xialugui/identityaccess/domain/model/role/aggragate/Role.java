package cn.xialugui.identityaccess.domain.model.role.aggragate;

import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * 角色聚合
 *
 * @author 夏露桂
 * @since 2021/7/14 19:38
 */
@Getter
@Entity
@NoArgsConstructor
public final class Role extends AbstractAggregateRoot<Role> {
    @EmbeddedId
    private RoleId id;
    private RoleName name;
}
