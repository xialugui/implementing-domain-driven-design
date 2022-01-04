package cn.xialugui.toolmanagement.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 夏露桂
 * @since 2021/12/29 21:38
 */
public interface ToolEntityRepository extends JpaRepository<ToolEntity, Long> {
    Page<ToolEntity> findAllByNameContains(String name, Pageable pageable);
}
