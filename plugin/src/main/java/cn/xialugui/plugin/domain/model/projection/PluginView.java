package cn.xialugui.plugin.domain.model.projection;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PluginView {
    @Id
    private Long id;
    private String name;
    private String description;
    private String icon;
}
