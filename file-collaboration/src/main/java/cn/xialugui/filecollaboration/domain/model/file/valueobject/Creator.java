package cn.xialugui.filecollaboration.domain.model.file.valueobject;

import cn.xialugui.filecollaboration.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter(AccessLevel.PRIVATE)
public final class Creator extends ValueObject<Creator> {

    private String id;

    public Creator(String id) {
        setId(id);
    }

    @Override
    protected boolean equalsTo(Creator other) {
        return false;
    }
}
