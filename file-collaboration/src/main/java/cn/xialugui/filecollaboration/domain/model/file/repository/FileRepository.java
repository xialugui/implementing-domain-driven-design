package cn.xialugui.filecollaboration.domain.model.file.repository;

import cn.xialugui.filecollaboration.domain.model.file.aggregate.File;
import cn.xialugui.filecollaboration.domain.model.file.valueobject.FileId;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 夏露桂
 * @since 2021/7/27 15:29
 */
public interface FileRepository extends CrudRepository<File, FileId> {
}
