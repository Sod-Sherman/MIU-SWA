package edu.miu.paymentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PaymentServiceApplication implements CommandLineRunner {
    @Autowired
    private PaymentServiceImpl paymentService;

    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        paymentService.sendMessageToBilledOrderEvent(" -----------------------TESTing billed-ORDER-event! ");
    }
}
