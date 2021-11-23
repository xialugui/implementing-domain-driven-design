package cn.xialugui.identityaccess;

import cn.xialugui.sharedkernel.infrastructure.persistence.EnableJwtJpaAuditing;
import com.lugew.winsin.web.configuration.EnableStandardRestfulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableStandardRestfulResponse
@EnableJwtJpaAuditing
public class IdentityAccessApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdentityAccessApplication.class, args);
    }


}
