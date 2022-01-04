package cn.xialugui.toolmanagement.command

import cn.xialugui.toolmanagement.StubAggregateLifecycleSpecification
import cn.xialugui.toolmanagement.command.api.PublishToolCommand
import cn.xialugui.toolmanagement.command.api.ToolId
import cn.xialugui.toolmanagement.command.api.ToolPublishedEvent
import cn.xialugui.toolmanagement.util.Environment
import cn.xialugui.toolmanagement.util.ToolUtil
import spock.lang.Subject
import spock.lang.Title

@Title("工具说明")
@Subject(Tool)
class PluginSpec extends StubAggregateLifecycleSpecification {
    def toolId = new ToolId()
    def name = "计算器"
    def description = "这是一个很好用的计算器，试试看吧"
    def icon = "https://xialugui.cn/folder/hello.icon"
    def filename = Environment.path() + "\\src\\test\\resources\\plugin\\Tool.zip"

    def "发布工具"() {
        setup:
        PublishToolCommand command =
                new PublishToolCommand(toolId, name, description, icon, filename)

        ToolPublishedEvent event = new ToolPublishedEvent(toolId, name, description, icon)
        when: "发布"
        def tool = new Tool(command)
        then: "已发布"
        expectEvent(event)
        and: "处理事件"
        tool.on(event)
        then: "修改状态"
        with(tool) {
            toolId == event.toolId
        }
        cleanup: "清除已安装的工具"
        ToolUtil.remove(toolId.identifier)
    }

}
