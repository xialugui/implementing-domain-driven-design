package cn.xialugui.plugin.command.api

/**
 *
 * @author 夏露桂
 * @since 2021/12/29 19:16
 */
data class PluginPublishedEvent(
        val pluginId: PluginId,
        val name: String,
        val description: String,
        val icon: String
)