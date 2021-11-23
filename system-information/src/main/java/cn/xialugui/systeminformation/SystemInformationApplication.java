package cn.xialugui.systeminformation;

import cn.xialugui.sharedkernel.infrastructure.persistence.EnableJwtJpaAuditing;
import com.lugew.winsin.web.configuration.EnableStandardRestfulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJwtJpaAuditing
@EnableStandardRestfulResponse
public class SystemInformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemInformationApplication.class, args);
    }

}
