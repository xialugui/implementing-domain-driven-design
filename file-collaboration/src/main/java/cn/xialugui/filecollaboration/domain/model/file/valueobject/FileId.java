package cn.xialugui.filecollaboration.domain.model.file.valueobject;

import cn.xialugui.sharedkernel.domain.model.NaturalId;
import cn.xialugui.sharedkernel.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class FileId extends ValueObject<FileId> implements NaturalId {

    private String value;

    public FileId(String value) {
        setValue(value);
    }

    @Override
    protected boolean equalsTo(FileId other) {
        return getValue().equals(other.getValue());
    }
}
