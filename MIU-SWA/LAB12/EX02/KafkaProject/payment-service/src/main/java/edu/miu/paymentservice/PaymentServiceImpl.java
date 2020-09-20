package edu.miu.paymentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.billed-order-event}")
    private String billed_order_event;

    @KafkaListener(topics = "${app.topic.order-created-event}")
    public void receiveOrderCreatedEvent(@Payload String message,
                        @Headers MessageHeaders headers) {
        System.out.println("************ Received message ********====> " + message);
        headers.keySet().forEach(key ->
                System.out.println("****** headers ******** key: " + key + " value: " + headers.get(key)));

        doPayment(message);

    }

    public void sendMessageToBilledOrderEvent(String message) {
        System.out.println("sending message=" + message + " to topic=" + billed_order_event);
        kafkaTemplate.send(billed_order_event, message);
    }

    private void doPayment(String order) {
        String orderSuccess = order + " ++++++++++++++++++ billed success";
        kafkaTemplate.send(billed_order_event, orderSuccess);
    }

}
