package integration;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {
	@Autowired
	private GreetingGateway gateway;
	
	@RequestMapping("/greeting/{name}")
	@Async
	public String getGreeting(@PathVariable("name") String name) {
		Message<String> helloMessage =  MessageBuilder.withPayload(name.toUpperCase()).build();

		String result = gateway.handleRequest(helloMessage);
		return result;
	}
}
