package edu.miu.webshopconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class WebShopConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebShopConfigServerApplication.class, args);
    }

}
