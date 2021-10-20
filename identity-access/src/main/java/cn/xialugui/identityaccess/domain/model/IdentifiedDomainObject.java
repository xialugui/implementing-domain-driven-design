package cn.xialugui.identityaccess.domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 层超类型
 * <p>
 * {@link IdentifiedDomainObject#pk}属性通过{@code protected}关键字隐藏了委派主键。
 * 所有实体都扩展自该抽象基类。在实体所处的模块之外，客户端根本就不用关心id这个委派标识。
 * 我们甚至可以将{@code protected}换为{@code private}。
 * </p>
 * <br>
 * <p>
 * 委派主键还可以作为外键与其他表关联， 这样提供了引用一致性。这可能出自
 * 数据管理上的需求或者为了支持一些工具。引用一致性对于Hibernate来说是重要
 * 的，特别是当保存any-to-any（比如1∶M）的数据模型时。另外，在从数据库中读取
 * 聚合时， 引用一致性还支持SQL的联合查询。
 * </p>
 *
 * @author 夏露桂
 * @since 2021/8/12 15:17
 */
@MappedSuperclass
public abstract class IdentifiedDomainObject<ID> implements Serializable, Identifiable<ID> {
    @Id
    @GeneratedValue
    private long pk = -1;

    protected long getPk() {
        return this.pk;
    }

    protected void setPk(long id) {
        this.pk = id;
    }
}
