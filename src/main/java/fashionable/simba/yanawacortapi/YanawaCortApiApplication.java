package fashionable.simba.yanawacortapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class YanawaCortApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YanawaCortApiApplication.class, args);
    }

}
