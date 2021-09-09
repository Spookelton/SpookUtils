package net.spookelton.spookutils.restAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiCore {
    public static void startAPI() {
        SpringApplication.run(RestApiCore.class);
    }
}
