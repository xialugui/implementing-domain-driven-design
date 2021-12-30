package cn.xialugui.plugin.query.api

import org.springframework.data.domain.Pageable

/**
 *
 * @author 夏露桂
 * @since 2021/12/29 20:15
 */
data class FindPlugins(val name: String, val pageable: Pageable);