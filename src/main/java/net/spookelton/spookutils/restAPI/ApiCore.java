package net.spookelton.spookutils.restAPI;

import net.spookelton.spookutils.config.ModConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ApiCore {
    public static void startAPI() {
        SpringApplication.run(ApiCore.class);
    }

    @Component
    public static class PortSetter implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

        @Override
        public void customize(ConfigurableWebServerFactory factory) {
            factory.setPort(ModConfig.restApi.port);
        }
    }
}
