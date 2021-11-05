package cn.xialugui.identityaccess.domain.model.role.aggregate;

import cn.xialugui.identityaccess.domain.model.HibernateAggregateRoot;
import cn.xialugui.identityaccess.domain.model.permission.aggregate.Permission;
import cn.xialugui.identityaccess.domain.model.permission.valueobject.PermissionId;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleId;
import cn.xialugui.identityaccess.domain.model.role.valueobject.RoleName;
import cn.xialugui.identityaccess.domain.model.user.valueobject.Username;
import com.lugew.winsin.core.exception.Exception;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色聚合
 *
 * @author 夏露桂
 * @since 2021/7/14 19:38
 */
@Getter
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class Role extends HibernateAggregateRoot<Role, RoleId> {
    public static final Role EMPTY = new Role();
    public static final Role RANDOM = new Role(RoleId.random(), null);
    /**
     * {@code @NaturalId}是{@code Hibernate}的注解，表达其不可变性，相当于{@link cn.xialugui.identityaccess.domain.model.user.aggregate.User#setUsername(Username)}
     * 方法。详情参考<a href="https://docs.jboss.org/hibernate/orm/5.5/userguide/html_single/Hibernate_User_Guide.html#naturalid"/>Natural Ids</a>
     **/
    @Embedded
    private @Valid @NotNull RoleName name;

    @ElementCollection
    private @NotNull Set<@Valid PermissionId> permissionIds;

    public Role(RoleId random, RoleName roleName) {
        setNaturalId(random);
        setName(roleName);
        setPermissionIds(new HashSet<>());
    }

    public void changeName(RoleName roleName) {
        setName(roleName);
    }


    public void addPermission(Permission permission) {
        if (null == permission || null == permission.naturalId()) {
            throw new Exception("permission.not.exist");
        }
        getPermissionIds().add(permission.naturalId());
    }

    public void updatePermissions(List<Permission> permissions) {
        if (null == permissions) {
            permissionIds = new HashSet<>();
        } else {
            permissionIds = permissions.stream().map(Permission::naturalId).collect(Collectors.toSet());
        }
    }
}
