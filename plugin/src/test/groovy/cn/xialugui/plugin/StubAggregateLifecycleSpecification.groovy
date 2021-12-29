package cn.xialugui.plugin

import org.axonframework.test.aggregate.StubAggregateLifecycleExtension
import spock.lang.Specification

/**
 * 聚合生命周期桩，使用Axon框架测试的测试类需要继承此类。
 */
abstract class StubAggregateLifecycleSpecification extends Specification {

    /**
     * 聚合根桩，用以模拟聚合环境。
     */
    StubAggregateLifecycleExtension lifecycle = new StubAggregateLifecycleExtension();

    /**
     * 每个测试都会重新激活聚合生命周期。
     */
    def setup() {
        lifecycle.activate()
    }

    /**
     * 每个测试在结束时都会结束聚合的生命周期。
     */
    def cleanup() {
        lifecycle.close()
    }

    void expectEvent(event) {
        with(lifecycle) {
            appliedEvents.size() == 1
            appliedEventPayloads[0] == event
        }
    }
}
