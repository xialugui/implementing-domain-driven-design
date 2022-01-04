package cn.xialugui.toolmanagement.command.api

import org.axonframework.modelling.command.TargetAggregateIdentifier

/**
 *
 * @author 夏露桂
 * @since 2021/12/29 14:59
 */
data class PublishToolCommand(
        @TargetAggregateIdentifier
        val toolId: ToolId = ToolId(),
        val name: String,
        val description: String,
        val icon: String,
        val filename: String
)

