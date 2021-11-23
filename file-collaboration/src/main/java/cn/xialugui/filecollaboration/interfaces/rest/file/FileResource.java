package cn.xialugui.filecollaboration.interfaces.rest.file;

import cn.xialugui.filecollaboration.application.FileApplicationService;
import cn.xialugui.filecollaboration.domain.model.file.aggregate.File;
import com.lugew.winsin.web.Standard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@Standard
@RequestMapping("files")
@RequiredArgsConstructor
public class FileResource {
    private final FileApplicationService applicationService;

    @PostMapping
    public void create(@RequestBody CreateFileCommand command) {
        applicationService.create(command);
    }

    @GetMapping
    public Page<File> findAll(Pageable pageable) {
        return applicationService.findAll(pageable);
    }


}
