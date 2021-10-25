package cn.xialugui.identityaccess.domain.model.permission.aggregate;

import cn.xialugui.identityaccess.domain.model.HibernateAggregateRoot;
import cn.xialugui.identityaccess.domain.model.permission.valueobject.PermissionId;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 权限聚合
 *
 * @author 夏露桂
 * @since 2021/9/6 20:59
 */
@Getter
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class Permission extends HibernateAggregateRoot<Permission, PermissionId> {

    /*    */

    public Permission(PermissionId naturalId, String name) {
        setNaturalId(naturalId);
        setName(name);
    }

    /**
     * 显然权限id是逻辑id，必须唯一。
     */
    /*
    @Embedded
    @NaturalId
    private @Valid @NotNull PermissionId permissionId;*/

    private @Pattern(regexp = "^[0-9a-zA-Z]{2,16}$") @NotNull String name;
}
