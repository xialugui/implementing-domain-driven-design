package cn.xialugui.toolmanagement.command.api

import cn.hutool.core.util.IdUtil

/**
 *
 * @author 夏露桂
 * @since 2021/12/29 19:16
 */
data class ToolId(val identifier: Long = IdUtil.getSnowflake().nextId()) {
    override fun toString(): String = identifier.toString();
}