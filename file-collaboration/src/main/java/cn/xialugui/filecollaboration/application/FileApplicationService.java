package cn.xialugui.filecollaboration.application;

import cn.xialugui.filecollaboration.domain.model.file.aggregate.File;
import cn.xialugui.filecollaboration.domain.model.file.repository.FileRepository;
import cn.xialugui.filecollaboration.interfaces.rest.file.CreateFileCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FileApplicationService {
    private final FileRepository repository;

    public void create(CreateFileCommand command) {
        repository.save(new File(command.getName()));
    }
}
