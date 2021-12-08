package cn.xialugui.systeminformation.domain.model.authenticationlog

import cn.xialugui.systeminformation.domain.model.authenticationlog.command.CreateAuthenticationSuccessLogCommand
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationSuccessLogCreatedEvent
import org.axonframework.test.aggregate.StubAggregateLifecycleExtension
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

import static org.axonframework.modelling.command.AggregateLifecycle.createNew

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
        createNew(AuthenticationLog, () -> new AuthenticationLog(new CreateAuthenticationSuccessLogCommand(id, name, ip, remarks, type, timestamp)))
//                .handle(new GenericCommandMessage<>(new CreateAuthenticationSuccessLogCommand(id, name, ip, remarks, type, timestamp)))
        /* apply(AuthenticationSuccessLogCreatedEvent.builder()
                 .authenticationLogId(id)
                 .name(name)
                 .remarks(remarks)
                 .type(type)
                 .timestamp(timestamp)
                 .ip(ip)
                 .build())*/
        then:
        with(lifecycle) {
            appliedEvents.size() == 1
            appliedEvents.get(0).payload == AuthenticationSuccessLogCreatedEvent.builder()
                    .authenticationLogId(id)
                    .name(name)
                    .remarks(remarks)
                    .type(type)
                    .timestamp(timestamp)
                    .ip(ip)
                    .build()
        }

        /* apply(AuthenticationSuccessLogCreatedEvent.builder()
                 .authenticationLogId(id)
                 .name(name)
                 .remarks(remarks)
                 .type(type)
                 .timestamp(timestamp)
                 .ip(ip)
                 .build())*/

        /* fixture.givenNoPriorActivity()
                 .when(new CreateAuthenticationSuccessLogCommand(id, name, ip, remarks, type, timestamp))
                 .expectEvents(AuthenticationSuccessLogCreatedEvent.builder()
                         .authenticationLogId(id)
                         .name(name)
                         .remarks(remarks)
                         .type(type)
                         .timestamp(timestamp)
                         .ip(ip)
                         .build())*/
    }
}
