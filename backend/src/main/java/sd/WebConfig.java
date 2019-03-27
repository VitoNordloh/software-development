package sd;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${database.uri}")
    private String databaseUri;

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    public @Bean MongoClient mongoClient() {
        MongoClientURI uri = new MongoClientURI(databaseUri);
        return new MongoClient(uri);
    }
}