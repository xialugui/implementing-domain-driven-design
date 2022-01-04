package cn.xialugui.toolmanagement.query

import javax.persistence.Entity
import javax.persistence.Id

/**
 *
 * @author 夏露桂
 * @since 2021/12/29 20:17
 */

@Entity
data class PluginEntity(
        @Id
        var id: Long,
        var name: String,
        var description: String,
        var icon: String
)


