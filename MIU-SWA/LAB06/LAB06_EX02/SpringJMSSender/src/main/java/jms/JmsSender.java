package jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {
	@Autowired
	JmsTemplate jmsTemplate;

	public void sendJMSMessage(Person person) {
		System.out.println("Sending a JMS message.");
		jmsTemplate.convertAndSend("testQueue",person);
	}
}
