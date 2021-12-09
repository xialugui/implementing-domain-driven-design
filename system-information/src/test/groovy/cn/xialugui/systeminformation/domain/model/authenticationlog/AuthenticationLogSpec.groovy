package cn.xialugui.systeminformation.domain.model.authenticationlog

import cn.xialugui.systeminformation.domain.model.authenticationlog.command.CreateAuthenticationSuccessLogCommand
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationSuccessLogCreatedEvent
import org.axonframework.test.aggregate.StubAggregateLifecycleExtension
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Subject
@Title("认证日志说明")
class AuthenticationLogSpec extends Specification {
    AuthenticationLogId id = new AuthenticationLogId()
    static StubAggregateLifecycleExtension lifecycle = new StubAggregateLifecycleExtension();


    def setup() {
        lifecycle.activate()
    }

    def cleanup() {
        lifecycle.close()
    }

    def '处理创建认证成功日志命令，发布已创建事件'() {
        when:
        def name = "hello";
        def ip = "127.0.0.1"
        def remarks = "no"
        def type = "null"
        def timestamp = System.currentTimeMillis()
        AuthenticationLog authenticationLog = new AuthenticationLog(
                new CreateAuthenticationSuccessLogCommand(id, name, ip, remarks, type, timestamp))


        then:
        with(lifecycle) {
            appliedEvents.size() == 1
            appliedEventPayloads[0] == AuthenticationSuccessLogCreatedEvent
                    .builder()
                    .authenticationLogId(id)
                    .name(name)
                    .remarks(remarks)
                    .type(type)
                    .timestamp(timestamp)
                    .ip(ip)
                    .build()
        }
    }
}
