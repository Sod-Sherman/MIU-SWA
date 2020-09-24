package edu.miu.speedservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import edu.miu.speedservice.service.impl.SpeedServiceImpl;

@SpringBootApplication
@EnableKafka
public class SpeedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpeedServiceApplication.class, args);
    }

}
