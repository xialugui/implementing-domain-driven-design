package cn.xialugui.filecollaboration;

import com.lugew.winsin.web.configuration.EnableStandardRestfulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableJpaAuditing
@EnableStandardRestfulResponse
@EnableResourceServer
public class FileCollaborationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCollaborationApplication.class, args);
    }

}
