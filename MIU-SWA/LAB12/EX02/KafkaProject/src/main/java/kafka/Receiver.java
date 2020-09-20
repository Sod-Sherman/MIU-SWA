package kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Receiver {

    @KafkaListener(topics = "${app.topic.greetingtopic}")
    public void receive(@Payload String message,
                        @Headers MessageHeaders headers) {
        System.out.println("received message=" + message);
        headers.keySet().forEach(key -> System.out.println(key + " : " + headers.get(key)));
    }

}