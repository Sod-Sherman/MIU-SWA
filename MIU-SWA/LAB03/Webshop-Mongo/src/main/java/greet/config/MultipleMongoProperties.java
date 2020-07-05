package greet.config;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author Marcos Barbero
 */
@Data
@ConfigurationProperties(prefix = "mongodb")
public class MultipleMongoProperties {

    private MongoProperties primary = new MongoProperties();
    private MongoProperties secondary = new MongoProperties();
	public MongoProperties getPrimary() {
		// TODO Auto-generated method stub
		return this.primary;
	}
	public MongoProperties getSecondary() {
		// TODO Auto-generated method stub
		return this.secondary;
	}
    	
}
