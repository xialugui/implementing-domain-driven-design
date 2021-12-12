package cn.xialugui.systeminformation.query.authenticationlog

import cn.xialugui.systeminformation.domain.model.authenticationlog.AuthenticationLogId
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationFailureLogCreatedEvent
import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationSuccessLogCreatedEvent
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title


@Title("认证日志事件处理器")
@Subject(AuthenticationLogEventHandler)
class AuthenticationLogEventHandlerSpec extends Specification {
    AuthenticationLogViewRepository repository = Mock()
    AuthenticationLogEventHandler eventHandler
    AuthenticationLogId authenticationLogId = new AuthenticationLogId()
    String name = "ddd"
    String ip = "127.0.0.1"
    String remarks = "客户端"
    String type = "cn.xialugui.Token"
    Long timestamp = System.currentTimeMillis()

    def setup() {
        eventHandler = new AuthenticationLogEventHandler(repository)
    }

    def "认证成功后保存日志"() {
        when: "认证成功日志创建事件发生"
        eventHandler.on(AuthenticationSuccessLogCreatedEvent.builder()
                .authenticationLogId(authenticationLogId)
                .name(name)
                .ip(ip)
                .remarks(remarks)
                .type(type)
                .timestamp(timestamp)
                .build())
        then: "已保存认证成功日志"
        interaction {
            entitySaved(AuthenticationType.SUCCESS)
        }
    }

    def "认证失败后保存日志"() {
        when: "认证失败日志创建事件发生"
        eventHandler.on(AuthenticationFailureLogCreatedEvent.builder()
                .authenticationLogId(authenticationLogId)
                .name(name)
                .ip(ip)
                .remarks(remarks)
                .type(type)
                .timestamp(timestamp)
                .build())
        then: "已保存认证失败日志"
        interaction {
            entitySaved(AuthenticationType.FAILURE)
        }
    }

    private void entitySaved(AuthenticationType authenticationType) {
        1 * repository.save((AuthenticationLogView authenticationLogView) ->
                with(authenticationLogView) {
                    name == this.name
                    ip == this.ip
                    remarks == this.remarks
                    type == authenticationType
                    timestamp == this.timestamp
                }
        )
    }

}
