package cn.xialugui.filecollaboration.domain.model.file.valueobject;

import cn.hutool.core.util.IdUtil;
import cn.xialugui.sharedkernel.domain.model.NaturalId;
import cn.xialugui.sharedkernel.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter(AccessLevel.PRIVATE)
public final class FileId extends ValueObject<FileId> implements NaturalId {

    private Long value;

    public FileId() {
        setValue(IdUtil.getSnowflake().nextId());
    }

    @Override
    protected boolean equalsTo(FileId other) {
        return getValue().equals(other.getValue());
    }
}
