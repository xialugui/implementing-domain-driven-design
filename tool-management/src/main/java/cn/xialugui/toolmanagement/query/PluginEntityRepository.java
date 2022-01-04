package cn.xialugui.toolmanagement.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 夏露桂
 * @since 2021/12/29 21:38
 */
public interface PluginEntityRepository extends JpaRepository<PluginEntity, Long> {
    Page<PluginEntity> findAllByNameContains(String name, Pageable pageable);
}
