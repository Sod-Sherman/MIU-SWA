package jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class PersonMessageListener {
 
    @JmsListener(destination = "testQueue")
    public void receiveMessage(final Person person) {
    	System.out.println("JMS receiver received message:" + person.getFirstName()+" "+person.getLastName());
    }
}

