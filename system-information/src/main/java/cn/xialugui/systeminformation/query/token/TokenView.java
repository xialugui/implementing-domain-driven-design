package cn.xialugui.systeminformation.query.token;

import cn.xialugui.sharedkernel.infrastructure.persistence.DefaultPersistable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class TokenView extends DefaultPersistable {
    private Long identifier;
    @Column(length = 1024)
    private String token;
    private String name;
    private Status status;
}
