package cn.xialugui.plugin.domain.model

import cn.hutool.core.util.IdUtil
import cn.xialugui.plugin.domain.model.command.PublishPluginCommand
import cn.xialugui.plugin.domain.model.event.PluginPublishedEvent
import spock.lang.Subject
import spock.lang.Title

@Title("插件说明")
@Subject(Plugin)
class PluginSpec extends StubAggregateLifecycleSpecification {
    def id = PluginId.builder().value(IdUtil.getSnowflake().nextId()).build()
    def name = "计算器"
    def description = "这是一个很好用的计算器，试试看吧"
    def icon = "https://xialugui.cn/folder/hello.icon"

    def "发布插件"() {
        setup:
        PublishPluginCommand command = PublishPluginCommand.builder()
                .id(id)
                .name(name)
                .description(description)
                .icon(icon)
                .build()
        PluginPublishedEvent event = PluginPublishedEvent.builder()
                .id(command.id.value)
                .name(command.name)
                .description(command.description)
                .icon(icon)
                .build()
        when: "发布"
        def plugin = new Plugin(command)
        then: "已发布"
        expectEvent(event)
        and: "处理事件"
        plugin.on(event)
        then: "修改状态"
        with(plugin) {
            id == PluginId.builder().value(event.id).build()
            name == event.name
            description == event.description
            icon == event.icon
        }
    }

}
