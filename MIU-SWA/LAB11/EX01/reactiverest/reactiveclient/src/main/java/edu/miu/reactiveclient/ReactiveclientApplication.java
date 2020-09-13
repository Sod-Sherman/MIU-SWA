package edu.miu.reactiveclient;

import edu.miu.reactiveclient.DTO.StockDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class ReactiveclientApplication {

    public static void main(String[] args) throws InterruptedException{
        Flux<StockDTO> result = WebClient
                .create("http://localhost:8080/stocks")
                .get()
                .retrieve()
                .bodyToFlux(StockDTO.class);

        LocalDateTime rightNowTime = LocalDateTime.now();
        String formatRightNowTime = rightNowTime.format(DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss"));
        result.subscribe(s -> {
            System.out.print(formatRightNowTime + " : ");
            System.out.println(s);
        });
        Thread.sleep(15000);
        //SpringApplication.run(ReactiveclientApplication.class, args);
    }

}
