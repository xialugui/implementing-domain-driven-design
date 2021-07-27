package cn.xialugui.filecollaboration.domain.model.file.valueobject;

import cn.xialugui.filecollaboration.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter(AccessLevel.PRIVATE)
public final class FileId extends ValueObject<FileId> {

    private String id;

    public FileId(String id) {
        setId(id);
    }

    @Override
    protected boolean equalsTo(FileId other) {
        return false;
    }
}
