package cn.xialugui.sharedkernel.infrastructure.persistence;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;

/**
 * @author 夏露桂
 * @since 2021/12/8 14:11
 */
@MappedSuperclass
public abstract class DefaultPersistable extends AbstractPersistable<Long> {

}
