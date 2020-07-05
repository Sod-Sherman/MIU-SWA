package greet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import static greet.config.PrimaryMongoConfig.MONGO_TEMPLATE;

/**
 * @author Marcos Barbero
 */
@Configuration
@EnableMongoRepositories(basePackages = "greet.repository.primary",
        mongoTemplateRef = MONGO_TEMPLATE)
public class PrimaryMongoConfig {

    protected static final String MONGO_TEMPLATE = "primaryMongoTemplate";
}
