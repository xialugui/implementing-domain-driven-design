package cn.xialugui.identityaccess.infrastructure.persistence;

import cn.xialugui.identityaccess.domain.model.user.aggregate.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class HibernateEntity extends AbstractAuditable<User, Long> {

}