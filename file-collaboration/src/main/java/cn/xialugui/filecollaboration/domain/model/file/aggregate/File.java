package cn.xialugui.filecollaboration.domain.model.file.aggregate;

import cn.xialugui.filecollaboration.domain.model.file.valueobject.FileId;
import cn.xialugui.sharedkernel.infrastructure.constraint.validator.Name;
import cn.xialugui.sharedkernel.infrastructure.persistence.AbstractAggregateRoot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 文件
 *
 * @author 夏露桂
 * @since 2021/7/27 15:26
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public final class File extends AbstractAggregateRoot<File, FileId> {
    /**
     * 文件名称，
     */
    @Name
    private String name;

    public File(String name) {
        setNaturalId(new FileId());
        this.name = name;
    }
}
