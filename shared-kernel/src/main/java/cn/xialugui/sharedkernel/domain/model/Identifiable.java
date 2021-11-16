package cn.xialugui.sharedkernel.domain.model;

import java.io.Serializable;

/**
 * 可识别接口，实现此接口通常意味着对象包含自然逻辑ID。
 * 一般情况下，由实体和聚合根实现此接口。详见{@link cn.xialugui.sharedkernel.infrastructure.persistence.Entity}
 *
 * @author 夏露桂
 * @see cn.xialugui.sharedkernel.infrastructure.persistence.Entity
 * @see cn.xialugui.sharedkernel.infrastructure.persistence.AbstractAggregateRoot
 * @since 2021/8/12 15:56
 */
public interface Identifiable<NID extends Serializable> {
    NID naturalId();
}
