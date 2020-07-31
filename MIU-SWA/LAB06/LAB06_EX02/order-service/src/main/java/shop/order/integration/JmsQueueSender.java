package shop.order.integration;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JmsQueueSender {
    @Autowired
    JmsTemplate jmsTemplate;

    public void sendJMSMessage(Object messageObject, String queueName) {
        log.info("Sending a JMS message: {}", messageObject);
        try {
            jmsTemplate.convertAndSend(queueName, messageObject);
        } catch (Exception e) {
            log.error(e);
        }
    }
}
