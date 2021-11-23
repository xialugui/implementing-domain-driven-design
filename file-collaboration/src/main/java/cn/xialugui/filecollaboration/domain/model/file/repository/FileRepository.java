package cn.xialugui.filecollaboration.domain.model.file.repository;

import cn.xialugui.filecollaboration.domain.model.file.aggregate.File;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author 夏露桂
 * @since 2021/7/27 15:29
 */
public interface FileRepository extends PagingAndSortingRepository<File, Long> {
}
