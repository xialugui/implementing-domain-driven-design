package cn.xialugui.filecollaboration;

import cn.xialugui.sharedkernel.infrastructure.persistence.EnableJwtJpaAuditing;
import com.lugew.winsin.web.configuration.EnableStandardRestfulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJwtJpaAuditing
@EnableStandardRestfulResponse
public class FileCollaborationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCollaborationApplication.class, args);
    }

}
