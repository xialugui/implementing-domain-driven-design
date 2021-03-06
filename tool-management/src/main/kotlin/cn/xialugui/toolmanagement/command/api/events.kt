package cn.xialugui.toolmanagement.command.api

/**
 *
 * @author 夏露桂
 * @since 2021/12/29 19:16
 */
data class ToolPublishedEvent(
        val toolId: ToolId,
        val name: String,
        val description: String,
        val icon: String
)