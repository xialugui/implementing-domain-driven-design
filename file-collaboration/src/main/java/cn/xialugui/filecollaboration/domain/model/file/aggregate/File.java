package cn.xialugui.filecollaboration.domain.model.file.aggregate;

import cn.xialugui.filecollaboration.domain.model.file.valueobject.Creator;
import cn.xialugui.filecollaboration.domain.model.file.valueobject.FileId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * 文件
 *
 * @author 夏露桂
 * @since 2021/7/27 15:26
 */
@Entity
@Getter
@NoArgsConstructor
public final class File extends AbstractAggregateRoot<File> {
    @EmbeddedId
    private FileId id;

    private Creator creator;
}
