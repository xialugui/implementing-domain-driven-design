package cn.xialugui.plugin.command

import cn.xialugui.plugin.StubAggregateLifecycleSpecification
import cn.xialugui.plugin.command.api.PluginId
import cn.xialugui.plugin.command.api.PluginPublishedEvent
import cn.xialugui.plugin.command.api.PublishPluginCommand
import cn.xialugui.plugin.util.Environment
import cn.xialugui.plugin.util.PluginUtil
import spock.lang.Subject
import spock.lang.Title

@Title("插件说明")
@Subject(Plugin)
class PluginSpec extends StubAggregateLifecycleSpecification {
    def pluginId = new PluginId()
    def name = "计算器"
    def description = "这是一个很好用的计算器，试试看吧"
    def icon = "https://xialugui.cn/folder/hello.icon"
    def filename = Environment.path() + "\\src\\test\\resources\\plugin\\Plugin.zip"

    def "发布插件"() {
        setup:
        PublishPluginCommand command =
                new PublishPluginCommand(pluginId, name, description, icon, filename)

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
        cleanup: "清除已安装的插件"
        PluginUtil.remove(pluginId.identifier)
    }

}
