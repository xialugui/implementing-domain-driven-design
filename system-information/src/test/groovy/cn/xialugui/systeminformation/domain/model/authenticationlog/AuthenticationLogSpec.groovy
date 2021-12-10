package cn.xialugui.systeminformation.domain.model.authenticationlog


import cn.xialugui.systeminformation.domain.model.authenticationlog.command.CreateAuthenticationFailureLogCommand
import cn.xialugui.systeminformation.domain.model.authenticationlog.command.CreateAuthenticationSuccessLogCommand
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationFailureLogCreatedEvent
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationSuccessLogCreatedEvent
import spock.lang.Subject
import spock.lang.Title

@Subject
@Title("认证日志说明")
class AuthenticationLogSpec extends StubAggregateLifecycleSpecification {
    AuthenticationLogId id = new AuthenticationLogId()
    def name = "hello";
    def ip = "127.0.0.1"
    def remarks = "no"
    def type = "null"
    def timestamp = System.currentTimeMillis()

    def '认证成功'() {
        when: '成功'
        new AuthenticationLog(
                new CreateAuthenticationSuccessLogCommand(id, name, ip, remarks, type, timestamp))
        then: '触发成功日志'
        expectEvent(AuthenticationSuccessLogCreatedEvent
                .builder()
                .authenticationLogId(id)
                .name(name)
                .remarks(remarks)
                .type(type)
                .timestamp(timestamp)
                .ip(ip)
                .build())
    }

    def '认证失败'() {
        when: '失败'
        new AuthenticationLog(
                new CreateAuthenticationFailureLogCommand(id, name, ip, remarks, type, timestamp))
        then: '出发失败日志'
        expectEvent(
                AuthenticationFailureLogCreatedEvent
                        .builder()
                        .authenticationLogId(id)
                        .name(name)
                        .remarks(remarks)
                        .type(type)
                        .timestamp(timestamp)
                        .ip(ip)
                        .build()
        )

    }
}
