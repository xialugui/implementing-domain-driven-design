package cn.xialugui.identityaccess.domain.model.role.aggragate;

import cn.xialugui.identityaccess.domain.model.AbstractAggregateRoot;
import cn.xialugui.identityaccess.domain.model.Identifier;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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
public final class Role extends AbstractAggregateRoot<Role> {
  /*  @Embedded
    @Valid
    @NotNull
    private RoleId roleId;
    @Embedded
    @Valid
    @NotNull
    private RoleName name;*/

    @NotNull
    private String nickname;

    @Override
    public Identifier identifier() {
        return null;
    }
}
