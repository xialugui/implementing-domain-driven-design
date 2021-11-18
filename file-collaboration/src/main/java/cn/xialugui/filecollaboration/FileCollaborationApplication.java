package cn.xialugui.filecollaboration;

import com.lugew.winsin.web.configuration.EnableStandardRestfulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableStandardRestfulResponse
public class FileCollaborationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCollaborationApplication.class, args);
    }

}
