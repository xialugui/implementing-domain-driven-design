package cn.xialugui.plugin.domain.model


import cn.xialugui.plugin.command.Plugin
import cn.xialugui.plugin.command.api.PluginId
import cn.xialugui.plugin.command.api.PluginPublishedEvent
import cn.xialugui.plugin.command.api.PublishPluginCommand
import spock.lang.Subject
import spock.lang.Title

@Title("插件说明")
@Subject(Plugin)
class PluginSpec extends StubAggregateLifecycleSpecification {
    def pluginId = new PluginId()
    def name = "计算器"
    def description = "这是一个很好用的计算器，试试看吧"
    def icon = "https://xialugui.cn/folder/hello.icon"

    def "发布插件"() {
        setup:
        PublishPluginCommand command =
                new PublishPluginCommand(pluginId, name, description, icon)

        PluginPublishedEvent event = new PluginPublishedEvent(pluginId, name, description, icon)
        when: "发布"
        def plugin = new Plugin(command)
        then: "已发布"
        expectEvent(event)
        and: "处理事件"
        plugin.on(event)
        then: "修改状态"
        with(plugin) {
            pluginId == event.pluginId
        }
    }

}
