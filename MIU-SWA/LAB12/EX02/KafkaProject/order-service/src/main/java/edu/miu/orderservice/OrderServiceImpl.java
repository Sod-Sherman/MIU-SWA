package edu.miu.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.order-topic}")
    private String topic;

    public void send(String message) {
        System.out.println("sending message=" + message + " to topic=" + topic);
        kafkaTemplate.send(topic, message);
    }

    @KafkaListener(topics = "${app.topic.billed-order-topic}")
    public void receiveOrderCreatedEvent(@Payload String message,
                                         @Headers MessageHeaders headers) {
        System.out.println("************ Received message ********====> " + message);
        headers.keySet().forEach(key ->
                System.out.println("****** headers ******** key: " + key + " ---------value: " + headers.get(key)));
    }
}
