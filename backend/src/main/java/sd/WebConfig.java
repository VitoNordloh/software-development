package sd;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    public @Bean MongoClient mongoClient() {
        MongoClientURI uri = new MongoClientURI("mongodb://admin:guc3XK9drxmH3vhZ@sd-shard-00-00-gmjiq.mongodb.net:27017,sd-shard-00-01-gmjiq.mongodb.net:27017,sd-shard-00-02-gmjiq.mongodb.net:27017/test?ssl=true&replicaSet=SD-shard-0&authSource=admin&retryWrites=true");
        return new MongoClient(uri);
    }
}