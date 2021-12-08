package cn.xialugui.systeminformation.domain.model.authenticationlog;

import cn.xialugui.systeminformation.domain.model.authenticationlog.event.AuthenticationSuccessLogCreatedEvent;
import org.axonframework.test.aggregate.StubAggregateLifecycleExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@DisplayName("test")
public class AuthenticationTests {

    AuthenticationLogId id = new AuthenticationLogId();
    @RegisterExtension
    static StubAggregateLifecycleExtension lifecycle = new StubAggregateLifecycleExtension();

    @Test
    public void test() {

        var name = "hello";

        var ip = "127.0.0.1";
        var remarks = "no";
        var type = "null";
        var timestamp = System.currentTimeMillis();
        apply(AuthenticationSuccessLogCreatedEvent.builder().authenticationLogId(id).name(name).remarks(remarks).type(type).timestamp(timestamp).ip(ip).build());
        assert 1 == lifecycle.getAppliedEvents().size();
    }
}
