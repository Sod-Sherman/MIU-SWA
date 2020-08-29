package integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.scheduling.annotation.Async;

@MessagingGateway
public interface GreetingGateway {

	@Gateway(requestChannel = "channelA")
	String handleRequest(Message<String> message);
}
