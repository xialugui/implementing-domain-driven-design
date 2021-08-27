package cn.xialugui.identityaccess;

import com.lugew.winsin.web.configuration.EnableStandardRestfulResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableStandardRestfulResponse
public class IdentityAccessApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdentityAccessApplication.class, args);
    }

}
