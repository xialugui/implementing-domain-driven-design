package cn.xialugui.filecollaboration.interfaces.rest.file;

import cn.xialugui.filecollaboration.application.FileApplicationService;
import com.lugew.winsin.web.Standard;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Standard
@RequestMapping("files")
@RequiredArgsConstructor
public class FileResource {
    private final FileApplicationService applicationService;

    @PostMapping
    public void create(CreateFileCommand command) {
        applicationService.create(command);
    }
}
