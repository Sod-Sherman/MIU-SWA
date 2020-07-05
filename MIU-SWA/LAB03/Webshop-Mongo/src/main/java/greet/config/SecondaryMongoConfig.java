package greet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static greet.config.SecondaryMongoConfig.MONGO_TEMPLATE;

/**
 * @author Marcos Barbero
 */
@Configuration
@EnableMongoRepositories(basePackages = "greet.repository.secondary",
        mongoTemplateRef = MONGO_TEMPLATE)
public class SecondaryMongoConfig {

    protected static final String MONGO_TEMPLATE = "secondaryMongoTemplate";
}
