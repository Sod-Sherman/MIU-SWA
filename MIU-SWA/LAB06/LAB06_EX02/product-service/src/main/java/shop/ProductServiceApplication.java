package shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class ProductServiceApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
    }
    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {

        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

//        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
//        typeIdMappings.put("items", QueueItem.class);
//
//        converter.setTypeIdMappings(typeIdMappings);
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("items");
//        return converter;

        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
