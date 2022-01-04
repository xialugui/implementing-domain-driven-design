package cn.xialugui.toolmanagement.query.api

import org.springframework.data.domain.Pageable

/**
 *
 * @author 夏露桂
 * @since 2021/12/29 20:15
 */
data class FindTools(val name: String, val pageable: Pageable)